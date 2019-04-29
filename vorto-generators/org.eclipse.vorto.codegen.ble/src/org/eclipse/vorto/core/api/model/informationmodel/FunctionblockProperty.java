/**
 */
package org.eclipse.vorto.core.api.model.informationmodel;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.vorto.core.api.model.datatype.Presence;

import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Functionblock Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty#getPresence <em>Presence</em>}</li>
 *   <li>{@link org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty#isMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty#getExtendedFunctionBlock <em>Extended Function Block</em>}</li>
 * </ul>
 *
 * @see org.eclipse.vorto.core.api.model.informationmodel.InformationModelPackage#getFunctionblockProperty()
 * @model
 * @generated
 */
public interface FunctionblockProperty extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.vorto.core.api.model.informationmodel.InformationModelPackage#getFunctionblockProperty_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.vorto.core.api.model.informationmodel.InformationModelPackage#getFunctionblockProperty_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(FunctionblockModel)
	 * @see org.eclipse.vorto.core.api.model.informationmodel.InformationModelPackage#getFunctionblockProperty_Type()
	 * @model
	 * @generated
	 */
	FunctionblockModel getType();

	/**
	 * Sets the value of the '{@link org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(FunctionblockModel value);

	/**
	 * Returns the value of the '<em><b>Presence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Presence</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Presence</em>' containment reference.
	 * @see #setPresence(Presence)
	 * @see org.eclipse.vorto.core.api.model.informationmodel.InformationModelPackage#getFunctionblockProperty_Presence()
	 * @model containment="true"
	 * @generated
	 */
	Presence getPresence();

	/**
	 * Sets the value of the '{@link org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty#getPresence <em>Presence</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Presence</em>' containment reference.
	 * @see #getPresence()
	 * @generated
	 */
	void setPresence(Presence value);

	/**
	 * Returns the value of the '<em><b>Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiplicity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiplicity</em>' attribute.
	 * @see #setMultiplicity(boolean)
	 * @see org.eclipse.vorto.core.api.model.informationmodel.InformationModelPackage#getFunctionblockProperty_Multiplicity()
	 * @model
	 * @generated
	 */
	boolean isMultiplicity();

	/**
	 * Sets the value of the '{@link org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty#isMultiplicity <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiplicity</em>' attribute.
	 * @see #isMultiplicity()
	 * @generated
	 */
	void setMultiplicity(boolean value);

	/**
	 * Returns the value of the '<em><b>Extended Function Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extended Function Block</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extended Function Block</em>' containment reference.
	 * @see #setExtendedFunctionBlock(FunctionBlock)
	 * @see org.eclipse.vorto.core.api.model.informationmodel.InformationModelPackage#getFunctionblockProperty_ExtendedFunctionBlock()
	 * @model containment="true"
	 * @generated
	 */
	FunctionBlock getExtendedFunctionBlock();

	/**
	 * Sets the value of the '{@link org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty#getExtendedFunctionBlock <em>Extended Function Block</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extended Function Block</em>' containment reference.
	 * @see #getExtendedFunctionBlock()
	 * @generated
	 */
	void setExtendedFunctionBlock(FunctionBlock value);

} // FunctionblockProperty
