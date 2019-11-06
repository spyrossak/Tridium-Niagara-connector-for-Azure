// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.
package com.microsoft.workflow.mqtt;

import com.tridiumx.mqttClientDriver.BAbstractMqttDriverDevice;
import com.tridiumx.mqttClientDriver.BAbstractMqttDriverNetwork;
import com.tridiumx.mqttClientDriver.point.BMqttClientDriverPointDeviceExt;
import com.tridiumx.mqttClientDriver.point.BMqttClientDriverPointFolder;
import com.tridiumx.mqttClientDriver.proxyExt.publishers.BMqttStringObjectPublishExt;
import com.tridiumx.mqttClientDriver.proxyExt.subscribers.BMqttStringObjectSubscribeExt;

import java.util.Arrays;

import javax.baja.bacnet.BBacnetDevice;
import javax.baja.bacnet.BBacnetNetwork;
import javax.baja.bacnet.point.BBacnetProxyExt;
import javax.baja.collection.BITable;
import javax.baja.collection.TableCursor;
import javax.baja.control.*;
import javax.baja.converters.*;
import javax.baja.job.BSimpleJob;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;
import javax.baja.tag.Tag;

@NiagaraType()

public class BBacnetToMqttJob
    extends BSimpleJob
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.microsoft.workflow.mqtt.BBacnetToMqttJob(290324688)1.0$ @*/
/* Generated Wed Mar 28 09:06:14 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BBacnetToMqttJob.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/


    @Override
    public void run(Context context) throws Exception {
        if(jobInitiater == null) throw new Exception("BacnetToMqtt Job Error = Set jobInitiator before running job.");
        doExportToMqtt();
    }

    private BAbstractMqttDriverNetwork _mqttNetwork = null;
    private BAbstractMqttDriverDevice _mqttDevice = null;
    public void doExportToMqtt()
    {
        if(jobInitiater.getGatewayName().equals("")) jobInitiater.setGatewayName(Sys.getStation().getStationName());
        BComponent drivers = Sys.getStation().get("Drivers").asComponent();
        BComponent[] all = drivers.getChildComponents();
        BBacnetNetwork bacnetNetwork = null;

        for(int i = 0; i < all.length; i++)
        {
            if(all[i] instanceof BBacnetNetwork)
                bacnetNetwork = (BBacnetNetwork)all[i];
            if(all[i] instanceof BAbstractMqttDriverNetwork)
                _mqttNetwork = (BAbstractMqttDriverNetwork)all[i];
        }

        if(_mqttNetwork != null)
            _mqttDevice = (BAbstractMqttDriverDevice)_mqttNetwork.get(jobInitiater.getMqttNetworkDeviceName()).asComponent();

        if(bacnetNetwork != null && _mqttDevice != null) {
            processBacnetNetwork(bacnetNetwork);
            // Instantiate MQTT Subscribe Point for Cloud2Device messages...
            BMqttStringObjectSubscribeExt mqttExt = new BMqttStringObjectSubscribeExt();
            mqttExt.setTopic("devices/"+_mqttDevice.getClientID()+"/messages/devicebound/#");
            BStringPoint mqttPoint = new BStringPoint();
            mqttPoint.setProxyExt(mqttExt);
            _mqttDevice.getPoints().add("Cloud2Device", mqttPoint);
        }
    }



    private void processBacnetNetwork(BBacnetNetwork network)
    {
        // Use BQL to fetch all of the BACnet devices in the station.
        BOrd ord = BOrd.make("slot:/|bql:select from bacnet:BacnetDevice");
        BITable result = (BITable)ord.resolve(Sys.getStation()).get();
        TableCursor c = result.cursor();

        // Loop through the fetched BACnet devices and run processBacnetDevice against it.
        while (c.next())
        {
            processBacnetDevice(((BBacnetDevice)c.get()));
        }
    }

    private void processBacnetDevice(BBacnetDevice device)
    {
        device.lease();
        String folderName = device.getName();
        String deviceName = Integer.toString(device.getConfig().getDeviceObject().getObjectId().getInstanceNumber());

        // Create or fetch existing publish point folder.
        BMqttClientDriverPointFolder destFolder = null;
        if(_mqttDevice.getPoints().get(folderName) != null)
        {
            destFolder = (BMqttClientDriverPointFolder)_mqttDevice.getPoints().get(folderName);
        }
        else
        {
            destFolder = new BMqttClientDriverPointFolder();
            _mqttDevice.getPoints().add(folderName, destFolder);
        }

        // Remove all points and builders that presently exist in the publishing folder.
        destFolder.removeAll();

        // Use BQL to fetch all of the points from the specific the BACnet controller.
        BOrd ord = BOrd.make(device.getSlotPath() + "|bql:select from control:ControlPoint where proxyExt.type like 'bacnet:%'");
        BITable result = (BITable)ord.resolve(Sys.getStation()).get();
        TableCursor c = result.cursor();

        // Loop through BACnet points and publish them to the IoT Hub Mqtt Device
        while (c.next())
        {
            try {
                BControlPoint p = (BControlPoint) c.get();
                BBacnetProxyExt ext = (BBacnetProxyExt) p.getProxyExt();
                BBacnetMessageBuilder builder = new BBacnetMessageBuilder();

                BLink objectIdLink = new BConversionLink(ext.getHandleOrd(), "objectId", "bacnetObjectIdentifier", true, new BValueToString());
                BLink valueLink = null;

                // Create the correct link type based on the source data type.
                if (p instanceof BNumericPoint) {
                    valueLink = new BConversionLink(p.getHandleOrd(), "out", "presentValue", true, new BStatusNumericToString());
                }
                if (p instanceof BBooleanPoint) {
                    valueLink = new BConversionLink(p.getHandleOrd(), "out", "presentValue", true, new BStatusBooleanToString());
                }
                if (p instanceof BEnumPoint) {
                    valueLink = new BConversionLink(p.getHandleOrd(), "out", "presentValue", true, new BStatusEnumToString());
                }
                if (p instanceof BStringPoint) {
                    valueLink = new BConversionLink(p.getHandleOrd(), "out", "presentValue", true, new BStatusValueToString());
                }

                if (valueLink != null) {
                    // Instantiate the new MQTT Publish Point
                    BStringWritable mqttPoint = new BStringWritable();
                    BMqttStringObjectPublishExt mqttExt = new BMqttStringObjectPublishExt();
                    mqttPoint.setProxyExt(mqttExt);
                    mqttExt.setTopic("devices/"+_mqttDevice.getClientID()+"/messages/events/%24.ct=application%2Fjson&%24.ce=utf-8");
                    mqttExt.setRetained(false);
                    //mqttExt.setPublishMessageOnChange(false);

                    // Add builder and Publish Point to the new device folder
                    destFolder.add(p.getName() + "_builder", builder);
                    destFolder.add(p.getName(), mqttPoint);

                    // Set static builder Attributes/Default Values
                    builder.setGatewayName(jobInitiater.getGatewayName());
                    builder.setDeviceName(deviceName);
                    builder.setDatapointName(p.getName());
                    // Ignore tags about current status of datapoint, i. e. consider only static tags
                    String[] ignoredTags = {"hs:curErr", "hs:curStatus", "hs:curVal", "hs:writeErr", "hs:writeLevel", "hs:writeStatus", "hs:writeVal"};
                    StringBuilder tags = new StringBuilder("\"Tags\":{");
                    String delimiter = "";
                    for (Tag tag : p.tags()) {
                        if (!Arrays.asList(ignoredTags).contains(tag.getId().getQName())) {
                            tags.append(delimiter);
                            delimiter = ",";
                            tags.append("\"" + tag.getId().getQName() + "\":\"" + tag.getValue().encodeToString() + "\"");
                        }
                    }
                    tags.append("}");
                    builder.setTags(tags.toString());

                    // Link values from source component
                    builder.add(null, objectIdLink); // Links object ID to the builder which will parse and transform to IoT message.
                    builder.add(null, valueLink); // Links the present value to the builder object for including in the IoT message.

                    // Link from the builder point to the
                    BConversionLink pubLink = new BConversionLink(builder.getHandleOrd(), "out", "in10", true, new BStringToStatusString()); // The link from the builder to Azure publish point.
                    mqttPoint.add(null, pubLink); // Links the value of the builder to the publish point for IoT Hub.
                }
            }
            catch(Exception ex)
            {
                System.out.println(ex.getStackTrace().toString());
            }
        }

    }


    public BBacnetToMqtt jobInitiater = null;
}
