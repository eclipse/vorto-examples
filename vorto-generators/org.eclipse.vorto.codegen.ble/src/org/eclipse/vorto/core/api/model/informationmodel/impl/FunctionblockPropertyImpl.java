/**
 */
package org.eclipse.vorto.core.api.model.informationmodel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.vorto.core.api.model.datatype.Presence;

import org.eclipse.vorto.core.api.model.functionblock.FunctionBlock;
import org.eclipse.vorto.core.api.model.functionblock.FunctionblockModel;

import org.eclipse.vorto.core.api.model.informationmodel.FunctionblockProperty;
import org.eclipse.vorto.core.api.model.informationmodel.InformationModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Functionblock Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.vorto.core.api.model.informationmodel.impl.FunctionblockPropertyImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.vorto.core.api.model.informationmodel.impl.FunctionblockPropertyImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.vorto.core.api.model.informationmodel.impl.FunctionblockPropertyImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.vorto.core.api.model.informationmodel.impl.FunctionblockPropertyImpl#getPresence <em>Presence</em>}</li>
 *   <li>{@link org.eclipse.vorto.core.api.model.informationmodel.impl.FunctionblockPropertyImpl#isMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link org.eclipse.vorto.core.api.model.informationmodel.impl.FunctionblockPropertyImpl#getExtendedFunctionBlock <em>Extended Function Block</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FunctionblockPropertyImpl extends MinimalEObjectImpl.Container implements FunctionblockProperty {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected FunctionblockModel type;

	/**
	 * The cached value of the '{@link #getPresence() <em>Presence</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPresence()
	 * @generated
	 * @ordered
	 */
	protected Presence presence;

	/**
	 * The default value of the '{@link #isMultiplicity() <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MULTIPLICITY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMultiplicity() <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected boolean multiplicity = MULTIPLICITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExtendedFunctionBlock() <em>Extended Function Block</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendedFunctionBlock()
	 * @generated
	 * @ordered
	 */
	protected FunctionBlock extendedFunctionBlock;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FunctionblockPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InformationModelPackage.Literals.FUNCTIONBLOCK_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InformationModelPackage.FUNCTIONBLOCK_PROPERTY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InformationModelPackage.FUNCTIONBLOCK_PROPERTY__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FunctionblockModel getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (FunctionblockModel)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, InformationModelPackage.FUNCTIONBLOCK_PROPERTY__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionblockModel basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(FunctionblockModel newType) {
		FunctionblockModel oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InformationModelPackage.FUNCTIONBLOCK_PROPERTY__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Presence getPresence() {
		return presence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPresence(Presence newPresence, NotificationChain msgs) {
		Presence oldPresence = presence;
		presence = newPresence;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationModelPackage.FUNCTIONBLOCK_PROPERTY__PRESENCE, oldPresence, newPresence);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPresence(Presence newPresence) {
		if (newPresence != presence) {
			NotificationChain msgs = null;
			if (presence != null)
				msgs = ((InternalEObject)presence).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationModelPackage.FUNCTIONBLOCK_PROPERTY__PRESENCE, null, msgs);
			if (newPresence != null)
				msgs = ((InternalEObject)newPresence).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationModelPackage.FUNCTIONBLOCK_PROPERTY__PRESENCE, null, msgs);
			msgs = basicSetPresence(newPresence, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InformationModelPackage.FUNCTIONBLOCK_PROPERTY__PRESENCE, newPresence, newPresence));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isMultiplicity() {
		return multiplicity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMultiplicity(boolean newMultiplicity) {
		boolean oldMultiplicity = multiplicity;
		multiplicity = newMultiplicity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InformationModelPackage.FUNCTIONBLOCK_PROPERTY__MULTIPLICITY, oldMultiplicity, multiplicity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FunctionBlock getExtendedFunctionBlock() {
		return extendedFunctionBlock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExtendedFunctionBlock(FunctionBlock newExtendedFunctionBlock, NotificationChain msgs) {
		FunctionBlock oldExtendedFunctionBlock = extendedFunctionBlock;
		extendedFunctionBlock = newExtendedFunctionBlock;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, InformationModelPackage.FUNCTIONBLOCK_PROPERTY__EXTENDED_FUNCTION_BLOCK, oldExtendedFunctionBlock, newExtendedFunctionBlock);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExtendedFunctionBlock(FunctionBlock newExtendedFunctionBlock) {
		if (newExtendedFunctionBlock != extendedFunctionBlock) {
			NotificationChain msgs = null;
			if (extendedFunctionBlock != null)
				msgs = ((InternalEObject)extendedFunctionBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - InformationModelPackage.FUNCTIONBLOCK_PROPERTY__EXTENDED_FUNCTION_BLOCK, null, msgs);
			if (newExtendedFunctionBlock != null)
				msgs = ((InternalEObject)newExtendedFunctionBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - InformationModelPackage.FUNCTIONBLOCK_PROPERTY__EXTENDED_FUNCTION_BLOCK, null, msgs);
			msgs = basicSetExtendedFunctionBlock(newExtendedFunctionBlock, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InformationModelPackage.FUNCTIONBLOCK_PROPERTY__EXTENDED_FUNCTION_BLOCK, newExtendedFunctionBlock, newExtendedFunctionBlock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__PRESENCE:
				return basicSetPresence(null, msgs);
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__EXTENDED_FUNCTION_BLOCK:
				return basicSetExtendedFunctionBlock(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__NAME:
				return getName();
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__DESCRIPTION:
				return getDescription();
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__PRESENCE:
				return getPresence();
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__MULTIPLICITY:
				return isMultiplicity();
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__EXTENDED_FUNCTION_BLOCK:
				return getExtendedFunctionBlock();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__NAME:
				setName((String)newValue);
				return;
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__TYPE:
				setType((FunctionblockModel)newValue);
				return;
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__PRESENCE:
				setPresence((Presence)newValue);
				return;
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__MULTIPLICITY:
				setMultiplicity((Boolean)newValue);
				return;
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__EXTENDED_FUNCTION_BLOCK:
				setExtendedFunctionBlock((FunctionBlock)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__TYPE:
				setType((FunctionblockModel)null);
				return;
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__PRESENCE:
				setPresence((Presence)null);
				return;
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__MULTIPLICITY:
				setMultiplicity(MULTIPLICITY_EDEFAULT);
				return;
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__EXTENDED_FUNCTION_BLOCK:
				setExtendedFunctionBlock((FunctionBlock)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__TYPE:
				return type != null;
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__PRESENCE:
				return presence != null;
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__MULTIPLICITY:
				return multiplicity != MULTIPLICITY_EDEFAULT;
			case InformationModelPackage.FUNCTIONBLOCK_PROPERTY__EXTENDED_FUNCTION_BLOCK:
				return extendedFunctionBlock != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", description: ");
		result.append(description);
		result.append(", multiplicity: ");
		result.append(multiplicity);
		result.append(')');
		return result.toString();
	}

} //FunctionblockPropertyImpl
