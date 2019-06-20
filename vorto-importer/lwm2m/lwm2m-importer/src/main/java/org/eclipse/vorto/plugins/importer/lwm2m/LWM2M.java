/**
 * Copyright (c) 2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference
// Implementation, v2.2.7
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2016.06.03 at 10:07:53 AM SGT
//


package org.eclipse.vorto.plugins.importer.lwm2m;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Object" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Description1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ObjectID" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="ObjectURN" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MultipleInstances">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="Multiple"/>
 *                         &lt;enumeration value="Single"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Mandatory">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="Mandatory"/>
 *                         &lt;enumeration value="Optional"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Resources">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Item" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="Operations">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;enumeration value="R"/>
 *                                             &lt;enumeration value="W"/>
 *                                             &lt;enumeration value="RW"/>
 *                                             &lt;enumeration value="E"/>
 *                                             &lt;enumeration value="RE"/>
 *                                             &lt;enumeration value="WE"/>
 *                                             &lt;enumeration value="RWE"/>
 *                                             &lt;enumeration value=""/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="MultipleInstances">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;enumeration value="Multiple"/>
 *                                             &lt;enumeration value="Single"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="Mandatory">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;enumeration value="Mandatory"/>
 *                                             &lt;enumeration value="Optional"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="Type">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                             &lt;enumeration value="String"/>
 *                                             &lt;enumeration value="Integer"/>
 *                                             &lt;enumeration value="Float"/>
 *                                             &lt;enumeration value="Boolean"/>
 *                                             &lt;enumeration value="Opaque"/>
 *                                             &lt;enumeration value="Time"/>
 *                                             &lt;enumeration value="Integer"/>
 *                                             &lt;enumeration value=""/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                       &lt;element name="RangeEnumeration" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="Units" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Description2" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *                 &lt;attribute name="ObjectType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"object"})
@XmlRootElement(name = "LWM2M")
public class LWM2M {

  @XmlElement(name = "Object", required = true)
  protected List<LWM2M.Object> object;

  /**
   * Gets the value of the object property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot. Therefore any
   * modification you make to the returned list will be present inside the JAXB object. This is why
   * there is not a <CODE>set</CODE> method for the object property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getObject().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link LWM2M.Object }
   * 
   * 
   */
  public List<LWM2M.Object> getObject() {
    if (object == null) {
      object = new ArrayList<LWM2M.Object>();
    }
    return this.object;
  }


  /**
   * <p>
   * Java class for anonymous complex type.
   * 
   * <p>
   * The following schema fragment specifies the expected content contained within this class.
   * 
   * <pre>
   * &lt;complexType>
   *   &lt;complexContent>
   *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *       &lt;sequence>
   *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *         &lt;element name="Description1" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *         &lt;element name="ObjectID" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
   *         &lt;element name="ObjectURN" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *         &lt;element name="MultipleInstances">
   *           &lt;simpleType>
   *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
   *               &lt;enumeration value="Multiple"/>
   *               &lt;enumeration value="Single"/>
   *             &lt;/restriction>
   *           &lt;/simpleType>
   *         &lt;/element>
   *         &lt;element name="Mandatory">
   *           &lt;simpleType>
   *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
   *               &lt;enumeration value="Mandatory"/>
   *               &lt;enumeration value="Optional"/>
   *             &lt;/restriction>
   *           &lt;/simpleType>
   *         &lt;/element>
   *         &lt;element name="Resources">
   *           &lt;complexType>
   *             &lt;complexContent>
   *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                 &lt;sequence>
   *                   &lt;element name="Item" maxOccurs="unbounded">
   *                     &lt;complexType>
   *                       &lt;complexContent>
   *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
   *                           &lt;sequence>
   *                             &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *                             &lt;element name="Operations">
   *                               &lt;simpleType>
   *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
   *                                   &lt;enumeration value="R"/>
   *                                   &lt;enumeration value="W"/>
   *                                   &lt;enumeration value="RW"/>
   *                                   &lt;enumeration value="E"/>
   *                                   &lt;enumeration value="RE"/>
   *                                   &lt;enumeration value="WE"/>
   *                                   &lt;enumeration value="RWE"/>
   *                                   &lt;enumeration value=""/>
   *                                 &lt;/restriction>
   *                               &lt;/simpleType>
   *                             &lt;/element>
   *                             &lt;element name="MultipleInstances">
   *                               &lt;simpleType>
   *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
   *                                   &lt;enumeration value="Multiple"/>
   *                                   &lt;enumeration value="Single"/>
   *                                 &lt;/restriction>
   *                               &lt;/simpleType>
   *                             &lt;/element>
   *                             &lt;element name="Mandatory">
   *                               &lt;simpleType>
   *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
   *                                   &lt;enumeration value="Mandatory"/>
   *                                   &lt;enumeration value="Optional"/>
   *                                 &lt;/restriction>
   *                               &lt;/simpleType>
   *                             &lt;/element>
   *                             &lt;element name="Type">
   *                               &lt;simpleType>
   *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
   *                                   &lt;enumeration value="String"/>
   *                                   &lt;enumeration value="Integer"/>
   *                                   &lt;enumeration value="Float"/>
   *                                   &lt;enumeration value="Boolean"/>
   *                                   &lt;enumeration value="Opaque"/>
   *                                   &lt;enumeration value="Time"/>
   *                                   &lt;enumeration value="Integer"/>
   *                                   &lt;enumeration value=""/>
   *                                 &lt;/restriction>
   *                               &lt;/simpleType>
   *                             &lt;/element>
   *                             &lt;element name="RangeEnumeration" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *                             &lt;element name="Units" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *                             &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *                           &lt;/sequence>
   *                           &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" />
   *                         &lt;/restriction>
   *                       &lt;/complexContent>
   *                     &lt;/complexType>
   *                   &lt;/element>
   *                 &lt;/sequence>
   *               &lt;/restriction>
   *             &lt;/complexContent>
   *           &lt;/complexType>
   *         &lt;/element>
   *         &lt;element name="Description2" type="{http://www.w3.org/2001/XMLSchema}string"/>
   *       &lt;/sequence>
   *       &lt;attribute name="ObjectType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
   *     &lt;/restriction>
   *   &lt;/complexContent>
   * &lt;/complexType>
   * </pre>
   * 
   * 
   */
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(name = "", propOrder = {"name", "description1", "objectID", "objectURN",
      "multipleInstances", "mandatory", "resources", "description2"})
  public static class Object {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Description1", required = true)
    protected String description1;
    @XmlElement(name = "ObjectID")
    @XmlSchemaType(name = "unsignedShort")
    protected int objectID;
    @XmlElement(name = "ObjectURN", required = true)
    protected String objectURN;
    @XmlElement(name = "MultipleInstances", required = true)
    protected String multipleInstances;
    @XmlElement(name = "Mandatory", required = true)
    protected String mandatory;
    @XmlElement(name = "Resources", required = true)
    protected LWM2M.Object.Resources resources;
    @XmlElement(name = "Description2", required = true)
    protected String description2;
    @XmlAttribute(name = "ObjectType", required = true)
    protected String objectType;

    /**
     * Gets the value of the name property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getName() {
      return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setName(String value) {
      this.name = value;
    }

    /**
     * Gets the value of the description1 property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDescription1() {
      return description1;
    }

    /**
     * Sets the value of the description1 property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDescription1(String value) {
      this.description1 = value;
    }

    /**
     * Gets the value of the objectID property.
     * 
     */
    public int getObjectID() {
      return objectID;
    }

    /**
     * Sets the value of the objectID property.
     * 
     */
    public void setObjectID(int value) {
      this.objectID = value;
    }

    /**
     * Gets the value of the objectURN property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getObjectURN() {
      return objectURN;
    }

    /**
     * Sets the value of the objectURN property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setObjectURN(String value) {
      this.objectURN = value;
    }

    /**
     * Gets the value of the multipleInstances property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getMultipleInstances() {
      return multipleInstances;
    }

    /**
     * Sets the value of the multipleInstances property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setMultipleInstances(String value) {
      this.multipleInstances = value;
    }

    /**
     * Gets the value of the mandatory property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getMandatory() {
      return mandatory;
    }

    /**
     * Sets the value of the mandatory property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setMandatory(String value) {
      this.mandatory = value;
    }

    /**
     * Gets the value of the resources property.
     * 
     * @return possible object is {@link LWM2M.Object.Resources }
     * 
     */
    public LWM2M.Object.Resources getResources() {
      return resources;
    }

    /**
     * Sets the value of the resources property.
     * 
     * @param value allowed object is {@link LWM2M.Object.Resources }
     * 
     */
    public void setResources(LWM2M.Object.Resources value) {
      this.resources = value;
    }

    /**
     * Gets the value of the description2 property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDescription2() {
      return description2;
    }

    /**
     * Sets the value of the description2 property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setDescription2(String value) {
      this.description2 = value;
    }

    /**
     * Gets the value of the objectType property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getObjectType() {
      return objectType;
    }

    /**
     * Sets the value of the objectType property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setObjectType(String value) {
      this.objectType = value;
    }


    /**
     * <p>
     * Java class for anonymous complex type.
     * 
     * <p>
     * The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Item" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Operations">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="R"/>
     *                         &lt;enumeration value="W"/>
     *                         &lt;enumeration value="RW"/>
     *                         &lt;enumeration value="E"/>
     *                         &lt;enumeration value="RE"/>
     *                         &lt;enumeration value="WE"/>
     *                         &lt;enumeration value="RWE"/>
     *                         &lt;enumeration value=""/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="MultipleInstances">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="Multiple"/>
     *                         &lt;enumeration value="Single"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="Mandatory">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="Mandatory"/>
     *                         &lt;enumeration value="Optional"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="Type">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                         &lt;enumeration value="String"/>
     *                         &lt;enumeration value="Integer"/>
     *                         &lt;enumeration value="Float"/>
     *                         &lt;enumeration value="Boolean"/>
     *                         &lt;enumeration value="Opaque"/>
     *                         &lt;enumeration value="Time"/>
     *                         &lt;enumeration value="Integer"/>
     *                         &lt;enumeration value=""/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                   &lt;element name="RangeEnumeration" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Units" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"item"})
    public static class Resources {

      @XmlElement(name = "Item", required = true)
      protected List<LWM2M.Object.Resources.Item> item;

      /**
       * Gets the value of the item property.
       * 
       * <p>
       * This accessor method returns a reference to the live list, not a snapshot. Therefore any
       * modification you make to the returned list will be present inside the JAXB object. This is
       * why there is not a <CODE>set</CODE> method for the item property.
       * 
       * <p>
       * For example, to add a new item, do as follows:
       * 
       * <pre>
       * getItem().add(newItem);
       * </pre>
       * 
       * 
       * <p>
       * Objects of the following type(s) are allowed in the list
       * {@link LWM2M.Object.Resources.Item }
       * 
       * 
       */
      public List<LWM2M.Object.Resources.Item> getItem() {
        if (item == null) {
          item = new ArrayList<LWM2M.Object.Resources.Item>();
        }
        return this.item;
      }


      /**
       * <p>
       * Java class for anonymous complex type.
       * 
       * <p>
       * The following schema fragment specifies the expected content contained within this class.
       * 
       * <pre>
       * &lt;complexType>
       *   &lt;complexContent>
       *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
       *       &lt;sequence>
       *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
       *         &lt;element name="Operations">
       *           &lt;simpleType>
       *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
       *               &lt;enumeration value="R"/>
       *               &lt;enumeration value="W"/>
       *               &lt;enumeration value="RW"/>
       *               &lt;enumeration value="E"/>
       *               &lt;enumeration value="RE"/>
       *               &lt;enumeration value="WE"/>
       *               &lt;enumeration value="RWE"/>
       *               &lt;enumeration value=""/>
       *             &lt;/restriction>
       *           &lt;/simpleType>
       *         &lt;/element>
       *         &lt;element name="MultipleInstances">
       *           &lt;simpleType>
       *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
       *               &lt;enumeration value="Multiple"/>
       *               &lt;enumeration value="Single"/>
       *             &lt;/restriction>
       *           &lt;/simpleType>
       *         &lt;/element>
       *         &lt;element name="Mandatory">
       *           &lt;simpleType>
       *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
       *               &lt;enumeration value="Mandatory"/>
       *               &lt;enumeration value="Optional"/>
       *             &lt;/restriction>
       *           &lt;/simpleType>
       *         &lt;/element>
       *         &lt;element name="Type">
       *           &lt;simpleType>
       *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
       *               &lt;enumeration value="String"/>
       *               &lt;enumeration value="Integer"/>
       *               &lt;enumeration value="Float"/>
       *               &lt;enumeration value="Boolean"/>
       *               &lt;enumeration value="Opaque"/>
       *               &lt;enumeration value="Time"/>
       *               &lt;enumeration value="Integer"/>
       *               &lt;enumeration value=""/>
       *             &lt;/restriction>
       *           &lt;/simpleType>
       *         &lt;/element>
       *         &lt;element name="RangeEnumeration" type="{http://www.w3.org/2001/XMLSchema}string"/>
       *         &lt;element name="Units" type="{http://www.w3.org/2001/XMLSchema}string"/>
       *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
       *       &lt;/sequence>
       *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" />
       *     &lt;/restriction>
       *   &lt;/complexContent>
       * &lt;/complexType>
       * </pre>
       * 
       * 
       */
      @XmlAccessorType(XmlAccessType.FIELD)
      @XmlType(name = "", propOrder = {"name", "operations", "multipleInstances", "mandatory",
          "type", "rangeEnumeration", "units", "description"})
      public static class Item {

        @XmlElement(name = "Name", required = true)
        protected String name;
        @XmlElement(name = "Operations", required = true)
        protected String operations;
        @XmlElement(name = "MultipleInstances", required = true)
        protected String multipleInstances;
        @XmlElement(name = "Mandatory", required = true)
        protected String mandatory;
        @XmlElement(name = "Type", required = true)
        protected String type;
        @XmlElement(name = "RangeEnumeration", required = true)
        protected String rangeEnumeration;
        @XmlElement(name = "Units", required = true)
        protected String units;
        @XmlElement(name = "Description", required = true)
        protected String description;
        @XmlAttribute(name = "ID", required = true)
        @XmlSchemaType(name = "unsignedShort")
        protected int id;

        /**
         * Gets the value of the name property.
         * 
         * @return possible object is {@link String }
         * 
         */
        public String getName() {
          return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value allowed object is {@link String }
         * 
         */
        public void setName(String value) {
          this.name = value;
        }

        /**
         * Gets the value of the operations property.
         * 
         * @return possible object is {@link String }
         * 
         */
        public String getOperations() {
          return operations;
        }

        /**
         * Sets the value of the operations property.
         * 
         * @param value allowed object is {@link String }
         * 
         */
        public void setOperations(String value) {
          this.operations = value;
        }

        /**
         * Gets the value of the multipleInstances property.
         * 
         * @return possible object is {@link String }
         * 
         */
        public String getMultipleInstances() {
          return multipleInstances;
        }

        /**
         * Sets the value of the multipleInstances property.
         * 
         * @param value allowed object is {@link String }
         * 
         */
        public void setMultipleInstances(String value) {
          this.multipleInstances = value;
        }

        /**
         * Gets the value of the mandatory property.
         * 
         * @return possible object is {@link String }
         * 
         */
        public String getMandatory() {
          return mandatory;
        }

        /**
         * Sets the value of the mandatory property.
         * 
         * @param value allowed object is {@link String }
         * 
         */
        public void setMandatory(String value) {
          this.mandatory = value;
        }

        /**
         * Gets the value of the type property.
         * 
         * @return possible object is {@link String }
         * 
         */
        public String getType() {
          return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value allowed object is {@link String }
         * 
         */
        public void setType(String value) {
          this.type = value;
        }

        /**
         * Gets the value of the rangeEnumeration property.
         * 
         * @return possible object is {@link String }
         * 
         */
        public String getRangeEnumeration() {
          return rangeEnumeration;
        }

        /**
         * Sets the value of the rangeEnumeration property.
         * 
         * @param value allowed object is {@link String }
         * 
         */
        public void setRangeEnumeration(String value) {
          this.rangeEnumeration = value;
        }

        /**
         * Gets the value of the units property.
         * 
         * @return possible object is {@link String }
         * 
         */
        public String getUnits() {
          return units;
        }

        /**
         * Sets the value of the units property.
         * 
         * @param value allowed object is {@link String }
         * 
         */
        public void setUnits(String value) {
          this.units = value;
        }

        /**
         * Gets the value of the description property.
         * 
         * @return possible object is {@link String }
         * 
         */
        public String getDescription() {
          return description;
        }

        /**
         * Sets the value of the description property.
         * 
         * @param value allowed object is {@link String }
         * 
         */
        public void setDescription(String value) {
          this.description = value;
        }

        /**
         * Gets the value of the id property.
         * 
         */
        public int getID() {
          return id;
        }

        /**
         * Sets the value of the id property.
         * 
         */
        public void setID(int value) {
          this.id = value;
        }

      }

    }

  }

}
