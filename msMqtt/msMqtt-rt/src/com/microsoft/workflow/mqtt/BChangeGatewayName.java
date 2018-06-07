package com.microsoft.workflow.mqtt;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.sys.*;

@NiagaraProperty(
        name="gatewayNameOption",
        type="BDynamicEnum",
        defaultValue="BDynamicEnum.make(0,BEnumRange.make(new String[]{\"Station$20Name\",\"Host$20ID\",\"Custom\"}))",
        flags= Flags.SUMMARY
)
@NiagaraProperty(
        name="UniqueGatewayName",
        type="String",
        defaultValue="CustomGatewayName",
        flags=Flags.SUMMARY
)
public class BChangeGatewayName
    extends BStruct
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.microsoft.workflow.mqtt.BChangeGatewayName(109932236)1.0$ @*/
/* Generated Tue Feb 27 11:45:41 PST 2018 by Slot-o-Matic (c) Tridium, Inc. 2012 */

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
  public static final Type TYPE = Sys.loadType(BChangeGatewayName.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
}
