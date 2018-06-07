// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT license.

package com.microsoft.workflow.mqtt;

import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.nre.util.TextUtil;
import javax.baja.sys.*;

@NiagaraType
@NiagaraProperty(
        name="out",
        defaultValue = "",
        type = "String",
        flags = Flags.SUMMARY | Flags.READONLY
)
@NiagaraProperty(
        name="gatewayName",
        defaultValue = "",
        type = "String",
        flags = Flags.SUMMARY
)
@NiagaraProperty(
        name="deviceName",
        defaultValue = "",
        type = "String",
        flags = Flags.SUMMARY
)
@NiagaraProperty(
        name="bacnetObjectIdentifier",
        defaultValue = "",
        type = "String",
        flags = Flags.SUMMARY
)
@NiagaraProperty(
        name="presentValue",
        defaultValue = "",
        type = "String",
        flags = Flags.SUMMARY
)
@NiagaraProperty(
        name="updateTime",
        defaultValue = "BRelTime.make(5001)",
        type = "BRelTime",
        flags = Flags.HIDDEN,
        facets = {
                @Facet(name= BFacets.SHOW_MILLISECONDS, value="true")
        }
)
@NiagaraAction(
        name="timerExpired",
        flags=Flags.HIDDEN
)
public class BBacnetMessageBuilder
extends BComponent
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.microsoft.workflow.mqtt.BBacnetMessageBuilder(626585737)1.0$ @*/
/* Generated Fri Feb 23 12:25:48 PST 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "out"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code out} property.
   * @see #getOut
   * @see #setOut
   */
  public static final Property out = newProperty(Flags.SUMMARY | Flags.READONLY, "", null);
  
  /**
   * Get the {@code out} property.
   * @see #out
   */
  public String getOut() { return getString(out); }
  
  /**
   * Set the {@code out} property.
   * @see #out
   */
  public void setOut(String v) { setString(out, v, null); }

////////////////////////////////////////////////////////////////
// Property "gatewayName"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code gatewayName} property.
   * @see #getGatewayName
   * @see #setGatewayName
   */
  public static final Property gatewayName = newProperty(Flags.SUMMARY, "", null);
  
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
// Property "deviceName"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code deviceName} property.
   * @see #getDeviceName
   * @see #setDeviceName
   */
  public static final Property deviceName = newProperty(Flags.SUMMARY, "", null);
  
  /**
   * Get the {@code deviceName} property.
   * @see #deviceName
   */
  public String getDeviceName() { return getString(deviceName); }
  
  /**
   * Set the {@code deviceName} property.
   * @see #deviceName
   */
  public void setDeviceName(String v) { setString(deviceName, v, null); }

////////////////////////////////////////////////////////////////
// Property "bacnetObjectIdentifier"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code bacnetObjectIdentifier} property.
   * @see #getBacnetObjectIdentifier
   * @see #setBacnetObjectIdentifier
   */
  public static final Property bacnetObjectIdentifier = newProperty(Flags.SUMMARY, "", null);
  
  /**
   * Get the {@code bacnetObjectIdentifier} property.
   * @see #bacnetObjectIdentifier
   */
  public String getBacnetObjectIdentifier() { return getString(bacnetObjectIdentifier); }
  
  /**
   * Set the {@code bacnetObjectIdentifier} property.
   * @see #bacnetObjectIdentifier
   */
  public void setBacnetObjectIdentifier(String v) { setString(bacnetObjectIdentifier, v, null); }

////////////////////////////////////////////////////////////////
// Property "presentValue"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code presentValue} property.
   * @see #getPresentValue
   * @see #setPresentValue
   */
  public static final Property presentValue = newProperty(Flags.SUMMARY, "", null);
  
  /**
   * Get the {@code presentValue} property.
   * @see #presentValue
   */
  public String getPresentValue() { return getString(presentValue); }
  
  /**
   * Set the {@code presentValue} property.
   * @see #presentValue
   */
  public void setPresentValue(String v) { setString(presentValue, v, null); }

////////////////////////////////////////////////////////////////
// Property "updateTime"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code updateTime} property.
   * @see #getUpdateTime
   * @see #setUpdateTime
   */
  public static final Property updateTime = newProperty(Flags.HIDDEN, BRelTime.make(5001), BFacets.make(BFacets.SHOW_MILLISECONDS, true));
  
  /**
   * Get the {@code updateTime} property.
   * @see #updateTime
   */
  public BRelTime getUpdateTime() { return (BRelTime)get(updateTime); }
  
  /**
   * Set the {@code updateTime} property.
   * @see #updateTime
   */
  public void setUpdateTime(BRelTime v) { set(updateTime, v, null); }

////////////////////////////////////////////////////////////////
// Action "timerExpired"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code timerExpired} action.
   * @see #timerExpired()
   */
  public static final Action timerExpired = newAction(Flags.HIDDEN, null);
  
  /**
   * Invoke the {@code timerExpired} action.
   * @see #timerExpired
   */
  public void timerExpired() { invoke(timerExpired, null, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BBacnetMessageBuilder.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    public BBacnetMessageBuilder()
    {
      /*

      */
    }

    String _HostId = Sys.getHostId();
    @Override
    public void started() throws Exception {
        super.started();
        if(!isRunning()) return;
        initTimer();
        _HostId = Sys.getHostId();
    }

    protected void initTimer()
    {
        if (ticket != null) ticket.cancel();
        ticket = Clock.schedulePeriodically(this, getUpdateTime(), timerExpired, null);
    }

    public void changed(Property p, Context cx)
    {
      if (!isRunning()) return;
      if (p.equals(updateTime))
      {
          initTimer();
      }
      String message = getMessage(cx);
      if(message != null)
        setOut(message);
    }

    private String getMessage(Context cx)
    {
      if(!isRunning()) return "";
      String result = null;
      try {
        String[] oid = TextUtil.split(getBacnetObjectIdentifier(), ':');
        if (oid.length > 0)
          result = "{\"GatewayName\":\"" + getGatewayName() + "\",\"Timestamp\":\"" + BAbsTime.now().encodeToString() + "\",\"Asset\":{\"DeviceName\":\"" + getDeviceName() + "\",\"ObjectType\":\"" + firstCharToUpper(oid[0]) + "\",\"Instance\":\"" + oid[1] + "\",\"PresentValue\":\"" + getPresentValue() + "\"}}";
      }catch(Exception ex){}
      return result;
    }

    String firstCharToUpper(String s) {
      return s.substring(0, 1).toUpperCase() +
              s.substring(1);
    }

    public void doTimerExpired(Context cx)
    {
      String message = getMessage(cx);
      if(message != null)
        setOut(message);
    }

    boolean lastInput;
    Clock.Ticket ticket;
}
