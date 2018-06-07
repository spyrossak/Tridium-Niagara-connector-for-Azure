// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.
package com.microsoft.workflow.mqtt;

import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.*;

@NiagaraType
@NiagaraProperty(
        name="iotSasToken",
        type="String",
        defaultValue="",
        flags= Flags.SUMMARY,
        facets = {
                @Facet(name=BFacets.MULTI_LINE, value="true"),
                @Facet(name=BFacets.FIELD_WIDTH, value="130")
        }
)
/*@NiagaraProperty(
        name="iotHubPort",
        type="BInteger",
        defaultValue="BInteger.make(8883)",
        flags= Flags.SUMMARY
)*/
@NiagaraProperty(
        name="publishInterval",
        type="BRelTime",
        defaultValue="BRelTime.makeSeconds(300)",
        flags= Flags.SUMMARY
)
@NiagaraProperty(
        name="gatewayNameOption",
        type="BDynamicEnum",
        defaultValue="BDynamicEnum.make(0,BEnumRange.make(new String[]{\"Station$20Name\",\"Host$20ID\",\"Custom\"}))",
        flags=Flags.SUMMARY
)
@NiagaraProperty(
        name="UniqueGatewayName",
        type="String",
        defaultValue="CustomGatewayName",
        flags=Flags.SUMMARY
)
public class BAddMqttDriver
extends BStruct
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.microsoft.workflow.mqtt.BAddMqttDriver(3619334206)1.0$ @*/
/* Generated Thu May 10 10:02:08 PDT 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "iotSasToken"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code iotSasToken} property.
   * @see #getIotSasToken
   * @see #setIotSasToken
   */
  public static final Property iotSasToken = newProperty(Flags.SUMMARY, "", BFacets.make(BFacets.make(BFacets.MULTI_LINE, true), BFacets.make(BFacets.FIELD_WIDTH, 130)));
  
  /**
   * Get the {@code iotSasToken} property.
   * @see #iotSasToken
   */
  public String getIotSasToken() { return getString(iotSasToken); }
  
  /**
   * Set the {@code iotSasToken} property.
   * @see #iotSasToken
   */
  public void setIotSasToken(String v) { setString(iotSasToken, v, null); }

////////////////////////////////////////////////////////////////
// Property "publishInterval"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code publishInterval} property.
   * @see #getPublishInterval
   * @see #setPublishInterval
   */
  public static final Property publishInterval = newProperty(Flags.SUMMARY, BRelTime.makeSeconds(300), null);
  
  /**
   * Get the {@code publishInterval} property.
   * @see #publishInterval
   */
  public BRelTime getPublishInterval() { return (BRelTime)get(publishInterval); }
  
  /**
   * Set the {@code publishInterval} property.
   * @see #publishInterval
   */
  public void setPublishInterval(BRelTime v) { set(publishInterval, v, null); }

////////////////////////////////////////////////////////////////
// Property "gatewayNameOption"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code gatewayNameOption} property.
   * @see #getGatewayNameOption
   * @see #setGatewayNameOption
   */
  public static final Property gatewayNameOption = newProperty(Flags.SUMMARY, BDynamicEnum.make(0,BEnumRange.make(new String[]{"Station$20Name","Host$20ID","Custom"})), null);
  
  /**
   * Get the {@code gatewayNameOption} property.
   * @see #gatewayNameOption
   */
  public BDynamicEnum getGatewayNameOption() { return (BDynamicEnum)get(gatewayNameOption); }
  
  /**
   * Set the {@code gatewayNameOption} property.
   * @see #gatewayNameOption
   */
  public void setGatewayNameOption(BDynamicEnum v) { set(gatewayNameOption, v, null); }

////////////////////////////////////////////////////////////////
// Property "UniqueGatewayName"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code UniqueGatewayName} property.
   * @see #getUniqueGatewayName
   * @see #setUniqueGatewayName
   */
  public static final Property UniqueGatewayName = newProperty(Flags.SUMMARY, "CustomGatewayName", null);
  
  /**
   * Get the {@code UniqueGatewayName} property.
   * @see #UniqueGatewayName
   */
  public String getUniqueGatewayName() { return getString(UniqueGatewayName); }
  
  /**
   * Set the {@code UniqueGatewayName} property.
   * @see #UniqueGatewayName
   */
  public void setUniqueGatewayName(String v) { setString(UniqueGatewayName, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BAddMqttDriver.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
    public BAddMqttDriver() { }

}
