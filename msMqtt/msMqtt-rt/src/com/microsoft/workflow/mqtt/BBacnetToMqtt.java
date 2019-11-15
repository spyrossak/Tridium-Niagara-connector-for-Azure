// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.
package com.microsoft.workflow.mqtt;

import com.tridiumx.mqttClientDriver.BAbstractMqttDriverDevice;
import com.tridiumx.mqttClientDriver.BAbstractMqttDriverNetwork;

import javax.baja.collection.BITable;
import javax.baja.collection.TableCursor;
import javax.baja.driver.point.BTuningPolicy;
import javax.baja.job.BJobService;
import javax.baja.naming.BOrd;
import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.nre.util.TextUtil;
import javax.baja.security.BPassword;
import javax.baja.security.BUsernameAndPassword;
import javax.baja.security.crypto.BSslTlsEnum;
import javax.baja.sys.*;

@NiagaraType
@NiagaraAction(
        name="installMqttDriver",
        flags=Flags.OPERATOR,
        parameterType = "BAddMqttDriver",
        defaultValue = "new BAddMqttDriver()"
)
@NiagaraAction(
        name="exportToMqtt",
        flags=Flags.OPERATOR
)
@NiagaraAction(
        name = "changeGatewayName",
        parameterType = "BChangeGatewayName",
        defaultValue="new BChangeGatewayName()",
        flags = Flags.SUMMARY
)
@NiagaraProperty(
        name="mqttNetworkDeviceName",
        flags=Flags.OPERATOR,
        type = "String",
        defaultValue = "AzureIotHub"
        /*The name of the MQTT device in the MqttNetwork*/
)
@NiagaraProperty(
        name="gatewayName",
        flags=Flags.OPERATOR,
        type = "String",
        defaultValue = ""
        /* this is used in the IotHub packet to identify the source device*/
)
@NiagaraProperty(
        name="readMe",
        flags=Flags.OPERATOR | Flags.READONLY,
        type = "String",
        defaultValue = "To use this utility, right click on this utility in the component tree and hover over the Actions option.\n\nUse the Install MqttDriver action to install and/or configure the AbstractMqttDriverNetwork.\n\nUse the Export to Mqtt action to export all BACnet points to the Azure IoT Hub.\n\nUse the Change Gateway Name action to change the gateway name associated with points exported from already deployed published points.",
        facets = {
                @Facet(name=BFacets.MULTI_LINE, value="true"),
                @Facet(name=BFacets.FIELD_WIDTH, value="130")
        }
)
/**
 * BBacnetToMqtt is a component that publishes points from BACnet Devices to the Tridium MQTT Driver.
 *
 * @author    Aaron K Unger - MacDonald Miller Facility Solutions
 * @creation  26 February 2018
 * @version   $Revision: 7$ $Date: 3/26/2018 3:35pm$
 */
public class BBacnetToMqtt
extends BComponent
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.microsoft.workflow.mqtt.BBacnetToMqtt(1423473261)1.0$ @*/
/* Generated Tue Feb 27 16:29:04 PST 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "mqttNetworkDeviceName"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code mqttNetworkDeviceName} property.
   * @see #getMqttNetworkDeviceName
   * @see #setMqttNetworkDeviceName
   */
  public static final Property mqttNetworkDeviceName = newProperty(Flags.OPERATOR, "AzureIotHub", null);
  
  /**
   * Get the {@code mqttNetworkDeviceName} property.
   * @see #mqttNetworkDeviceName
   */
  public String getMqttNetworkDeviceName() { return getString(mqttNetworkDeviceName); }
  
  /**
   * Set the {@code mqttNetworkDeviceName} property.
   * @see #mqttNetworkDeviceName
   */
  public void setMqttNetworkDeviceName(String v) { setString(mqttNetworkDeviceName, v, null); }

////////////////////////////////////////////////////////////////
// Property "gatewayName"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code gatewayName} property.
   * @see #getGatewayName
   * @see #setGatewayName
   */
  public static final Property gatewayName = newProperty(Flags.OPERATOR, "", null);
  
  /**
   * Get the {@code gatewayName} property.
   * @see #gatewayName
   */
  public String getGatewayName() { return getString(gatewayName); }
  
  /**
   * Set the {@code gatewayName} property.
   * @see #gatewayName
   */
  public void setGatewayName(String v) { setString(gatewayName, v, null); }

////////////////////////////////////////////////////////////////
// Property "readMe"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code readMe} property.
   * @see #getReadMe
   * @see #setReadMe
   */
  public static final Property readMe = newProperty(Flags.OPERATOR | Flags.READONLY, "To use this utility, right click on this utility in the component tree and hover over the Actions option.\n\nUse the Install MqttDriver action to install and/or configure the AbstractMqttDriverNetwork.\n\nUse the Export to Mqtt action to export all BACnet points to the Azure IoT Hub.\n\nUse the Change Gateway Name action to change the gateway name associated with points exported from already deployed published points.", BFacets.make(BFacets.make(BFacets.MULTI_LINE, true), BFacets.make(BFacets.FIELD_WIDTH, 130)));
  
  /**
   * Get the {@code readMe} property.
   * @see #readMe
   */
  public String getReadMe() { return getString(readMe); }
  
  /**
   * Set the {@code readMe} property.
   * @see #readMe
   */
  public void setReadMe(String v) { setString(readMe, v, null); }

////////////////////////////////////////////////////////////////
// Action "installMqttDriver"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code installMqttDriver} action.
   * @see #installMqttDriver(BAddMqttDriver parameter)
   */
  public static final Action installMqttDriver = newAction(Flags.OPERATOR, new BAddMqttDriver(), null);
  
  /**
   * Invoke the {@code installMqttDriver} action.
   * @see #installMqttDriver
   */
  public void installMqttDriver(BAddMqttDriver parameter) { invoke(installMqttDriver, parameter, null); }

////////////////////////////////////////////////////////////////
// Action "exportToMqtt"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code exportToMqtt} action.
   * @see #exportToMqtt()
   */
  public static final Action exportToMqtt = newAction(Flags.OPERATOR, null);
  
  /**
   * Invoke the {@code exportToMqtt} action.
   * @see #exportToMqtt
   */
  public void exportToMqtt() { invoke(exportToMqtt, null, null); }

////////////////////////////////////////////////////////////////
// Action "changeGatewayName"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code changeGatewayName} action.
   * @see #changeGatewayName(BChangeGatewayName parameter)
   */
  public static final Action changeGatewayName = newAction(Flags.SUMMARY, new BChangeGatewayName(), null);
  
  /**
   * Invoke the {@code changeGatewayName} action.
   * @see #changeGatewayName
   */
  public void changeGatewayName(BChangeGatewayName parameter) { invoke(changeGatewayName, parameter, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BBacnetToMqtt.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
    public BBacnetToMqtt()
    {
    }

    public void doChangeGatewayName(BChangeGatewayName parameters) throws Exception
    {
        BOrd ord = BOrd.make("slot:/|bql:select from msMqtt:BacnetMessageBuilder");
        BITable result = (BITable)ord.resolve(Sys.getStation()).get();
        TableCursor c = result.cursor();

        String gateway = getGatewayNameOption(parameters);

        // Loop through BACnet points and publish them to the IoT Hub Mqtt Device
        while (c.next()) {
            //try {
            BBacnetMessageBuilder builder = (BBacnetMessageBuilder) c.get();
            builder.setGatewayName(gateway);
            //}catch(Exception ex){}
        }
        setGatewayName(gateway);
    }

    public void doExportToMqtt()
    {
        BBacnetToMqttJob job = new BBacnetToMqttJob();
        job.jobInitiater = this;
        BJobService service = (BJobService)Sys.getService(BJobService.TYPE);
        service.submit(job, null);
    }

    public void doInstallMqttDriver(BAddMqttDriver parameters)
    {
        String DeviceName = null;
        String SharedAccessSignature = null;
        String IotHubHostName = null;

        String[] token = TextUtil.split(parameters.getIotSasToken(),';');
        IotHubHostName = TextUtil.split(token[0],'=')[1];
        DeviceName = TextUtil.split(token[1],'=')[1];
        SharedAccessSignature = token[2].replace("SharedAccessSignature=","").replace("\n","").replace("\r","");

        BComponent drivers = Sys.getStation().get("Drivers").asComponent();
        BComponent[] all = drivers.getChildComponents();

        BAbstractMqttDriverNetwork mqttDriver = null;
        for(int i = 0; i < all.length; i++)
        {
            if(all[i] instanceof BAbstractMqttDriverNetwork) {
                mqttDriver = (BAbstractMqttDriverNetwork)all[i];
                break;
            }
        }

        BAbstractMqttDriverDevice device = null;
        if(mqttDriver == null)
        {
            mqttDriver = new BAbstractMqttDriverNetwork();
            drivers.add("AbstractMqttDriverNetwork",mqttDriver);
            device = new BAbstractMqttDriverDevice();
            mqttDriver.add(getMqttNetworkDeviceName(), device);
        }
        else
        {
            if(mqttDriver.get(getMqttNetworkDeviceName()) == null) {
                device = new BAbstractMqttDriverDevice();
                mqttDriver.add(getMqttNetworkDeviceName(), device);
            }
            else{
                device = (BAbstractMqttDriverDevice)mqttDriver.get(getMqttNetworkDeviceName()).asComponent();
            }
        }

        BTuningPolicy policy = mqttDriver.getTuningPolicies().getDefaultPolicy();
        policy.setMinWriteTime((BRelTime)parameters.getPublishInterval().newCopy());
        policy.setMaxWriteTime((BRelTime)parameters.getPublishInterval().newCopy());

        String gateway = getGatewayName(parameters);
        // Set configuration settings to the Mqtt device.
        BUsernameAndPassword cred = new BUsernameAndPassword();
        cred.setUsername(IotHubHostName + "/" + DeviceName + "/?api-version=2018-06-30");
        cred.setPassword(BPassword.make(SharedAccessSignature));
        device.setClientID(DeviceName);
        device.setBrokerIpAddress(IotHubHostName);
        device.setBrokerPort(8883);
        device.setUsernameAndPassword(cred);
        device.setTopicForLWT(gateway);
        device.setCleanSession(true);
        device.setSslVersion(BSslTlsEnum.tlsv1_2);

        // Save Gateway Name
        setGatewayName(gateway);
    }

    String getGatewayName(BAddMqttDriver parameters)
    {
        String result = null;
        switch(parameters.getGatewayNameOption().getOrdinal()) {
            case 0:
                result = Sys.getStation().getStationName();
                break;
            case 1 :
                result = Sys.getHostId();
                break;
            default:
                result = parameters.getUniqueGatewayName();
                break;
        }
        return result;
    }

    String getGatewayNameOption(BChangeGatewayName parameters)
    {
        String result = null;
        switch(parameters.getGatewayNameOption().getOrdinal()) {
            case 0:
                result = Sys.getStation().getStationName();
                break;
            case 1 :
                result = Sys.getHostId();
                break;
            default:
                result = parameters.getUniqueGatewayName();
                break;
        }
        return result;
    }

    /* This is a response from tridium support for dynamically setting the value for an action parameter.
    @Override
    public BValue getActionParameterDefault(Action action)
    {
        if (action == something)
        {
            BMyStruct defaultStruct = new BMyStruct();
            defaultStruct.setSource(getStationName());
            return defaultStruct;
        }
        return super.getActionParameterDefault(action);
    }
    */
}

