package org.springframework.security.boot;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.acls.model.AclCache;

/**
 * <p>Configuration properties.</p>
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = SecurityAclProperties.PREFIX)
public class SecurityAclProperties {

	public static final String PREFIX = "spring.security.acl";

	/**
	 * Enable Security ACL.
	 */
	private boolean enabled = false;

	private boolean forcePrincipalAsString = false;
	protected boolean hideUserNotFoundExceptions = true;

	private boolean useAuthenticationRequestCredentials = true;

	private String[] ldapUrls;

	/** The url of the LDAP server. */
	private String[] urls;

	private boolean pooled = false;

	private String groupSearchBase = "";

	private boolean anonymousReadOnly = false;

	private String referral = null;

	/** ldap://192.168.0.1:389/dc=gnetis,dc=com */
	private String providerUrl;

	/** cn=Manager,dc=gnetis,dc=com */
	private String userDn;

	private String password;

	/**
	 * The base suffix from which all operations should origin. If a base suffix is
	 * set, you will not have to (and, indeed, must not) specify the full
	 * distinguished names in any operations performed.
	 */
	private String base;

	private Map<String, Object> baseEnvironmentProperties;

	private boolean cacheEnvironmentProperties = true;
	/** FilterBasedLdapUserSearch */

	/**
	 * Context name to search in, relative to the base of the configured
	 * ContextSource.
	 */
	private String searchBase = "";

	/**
	 * The filter expression used in the user search. This is an LDAP search filter
	 * (as defined in 'RFC 2254') with optional arguments. See the documentation for
	 * the <tt>search</tt> methods in {@link javax.naming.directory.DirContext
	 * DirContext} for more information.
	 *
	 * <p>
	 * In this case, the username is the only parameter.
	 * </p>
	 * Possible examples are:
	 * <ul>
	 * <li>(uid={0}) - this would search for a username match on the uid
	 * attribute.</li>
	 * </ul>
	 */
	private String searchFilter;

	/** The derefLinkFlag value as defined in SearchControls.. */
	private boolean derefLinkFlag;
	/**
	 * Specifies the attributes that will be returned as part of the search.
	 * <p>
	 * null indicates that all attributes will be returned. An empty array indicates
	 * no attributes are returned.
	 */
	public String[] returningAttrs = new String[] {};
	/**
	 * If true then searches the entire subtree as identified by context, if false
	 * (the default) then only searches the level identified by the context.
	 */
	private boolean searchSubtree;
	/**
	 * The time to wait before the search fails (in milliseconds); the default is
	 * zero, meaning forever.
	 */
	private int searchTimeLimit;

	// ~ Instance fields
	// ================================================================================================
	private boolean aclClassIdSupported;
	
	private boolean foreignKeysInDatabase = true;
	private String deleteEntryByObjectIdentityForeignKeySql = "delete from acl_entry where acl_object_identity=?";
	private String deleteObjectIdentityByPrimaryKeySql = "delete from acl_object_identity where id=?";
	/**
	 * 查询刚刚新增的acl_class的主键的SQL
	 */
	private String classIdentityQuerySql = "select seq_acl_class.currval from dual";
	/**
	 * 查询刚刚新增的acl_sid的主键的SQL
	 */
	private String sidIdentityQuerySql =  "select seq_acl_sid.currval from dual";
	/**
	 * 指定新增acl_class的脚本
	 */
	private String insertClassSql = "insert into acl_class(id, class) values (seq_acl_class.nextval, ?)";
	/**
	 * 指定新增acl_entry的脚本
	 */
	private String insertEntrySql =  "insert into acl_entry(id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) values (seq_acl_entry.nextval, ?, ?, ?, ?, ?, ?, ?)";
	/**
	 * 指定新增acl_object_identity的脚本
	 */
	private String insertObjectIdentitySql = "insert into acl_object_identity(id, object_id_class, object_id_identity, owner_sid, entries_inheriting) values(seq_acl_object_identity.nextval, ?, ?, ?, ?)";
	/**
	 * 指定新增acl_sid的脚本
	 */
	private String insertSidSql = "insert into acl_sid(id, principal, sid) values (seq_acl_sid.nextval, ?, ?)";
	private String selectClassPrimaryKeySql = "select id from acl_class where class=?";
	private String selectObjectIdentityPrimaryKeySql = "select acl_object_identity.id from acl_object_identity, acl_class "
			+ "where acl_object_identity.object_id_class = acl_class.id and acl_class.class=? "
			+ "and acl_object_identity.object_id_identity = ?";
	private String selectSidPrimaryKeySql = "select id from acl_sid where principal=? and sid=?";
	private String updateObjectIdentitySql = "update acl_object_identity set "
			+ "parent_object = ?, owner_sid = ?, entries_inheriting = ?" + " where id = ?";

	/**
	 * Returns the force principal as string.
	 *
	 * @return the force principal as string
	 */
	public boolean isForcePrincipalAsString() {
		return forcePrincipalAsString;
	}

	/**
	 * Sets the force principal as string.
	 *
	 * @param forcePrincipalAsString the force principal as string
	 */
	public void setForcePrincipalAsString(boolean forcePrincipalAsString) {
		this.forcePrincipalAsString = forcePrincipalAsString;
	}

	/**
	 * Returns the enabled.
	 *
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Returns the use authentication request credentials.
	 *
	 * @return the use authentication request credentials
	 */
	public boolean isUseAuthenticationRequestCredentials() {
		return useAuthenticationRequestCredentials;
	}

	/**
	 * Sets the use authentication request credentials.
	 *
	 * @param useAuthenticationRequestCredentials the use authentication request credentials
	 */
	public void setUseAuthenticationRequestCredentials(boolean useAuthenticationRequestCredentials) {
		this.useAuthenticationRequestCredentials = useAuthenticationRequestCredentials;
	}

	/**
	 * Returns the ldap urls.
	 *
	 * @return the ldap urls
	 */
	public String[] getLdapUrls() {
		return ldapUrls;
	}

	/**
	 * Sets the ldap urls.
	 *
	 * @param ldapUrls the ldap urls
	 */
	public void setLdapUrls(String[] ldapUrls) {
		this.ldapUrls = ldapUrls;
	}

	/**
	 * Returns the urls.
	 *
	 * @return the urls
	 */
	public String[] getUrls() {
		return urls;
	}

	/**
	 * Sets the urls.
	 *
	 * @param urls the urls
	 */
	public void setUrls(String[] urls) {
		this.urls = urls;
	}

	/**
	 * Returns the pooled.
	 *
	 * @return the pooled
	 */
	public boolean isPooled() {
		return pooled;
	}

	/**
	 * Sets the pooled.
	 *
	 * @param pooled the pooled
	 */
	public void setPooled(boolean pooled) {
		this.pooled = pooled;
	}

	/**
	 * Returns the group search base.
	 *
	 * @return the group search base
	 */
	public String getGroupSearchBase() {
		return groupSearchBase;
	}

	/**
	 * Sets the group search base.
	 *
	 * @param groupSearchBase the group search base
	 */
	public void setGroupSearchBase(String groupSearchBase) {
		this.groupSearchBase = groupSearchBase;
	}

	/**
	 * Returns the anonymous read only.
	 *
	 * @return the anonymous read only
	 */
	public boolean isAnonymousReadOnly() {
		return anonymousReadOnly;
	}

	/**
	 * Sets the anonymous read only.
	 *
	 * @param anonymousReadOnly the anonymous read only
	 */
	public void setAnonymousReadOnly(boolean anonymousReadOnly) {
		this.anonymousReadOnly = anonymousReadOnly;
	}

	/**
	 * Returns the referral.
	 *
	 * @return the referral
	 */
	public String getReferral() {
		return referral;
	}

	/**
	 * Sets the referral.
	 *
	 * @param referral the referral
	 */
	public void setReferral(String referral) {
		this.referral = referral;
	}

	/**
	 * Returns the provider url.
	 *
	 * @return the provider url
	 */
	public String getProviderUrl() {
		return providerUrl;
	}

	/**
	 * Sets the provider url.
	 *
	 * @param providerUrl the provider url
	 */
	public void setProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
	}

	/**
	 * Returns the user dn.
	 *
	 * @return the user dn
	 */
	public String getUserDn() {
		return userDn;
	}

	/**
	 * Sets the user dn.
	 *
	 * @param userDn the user dn
	 */
	public void setUserDn(String userDn) {
		this.userDn = userDn;
	}

	/**
	 * Returns the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the base.
	 *
	 * @return the base
	 */
	public String getBase() {
		return base;
	}

	/**
	 * Sets the base.
	 *
	 * @param base the base
	 */
	public void setBase(String base) {
		this.base = base;
	}

	/**
	 * Returns the base environment properties.
	 *
	 * @return the base environment properties
	 */
	public Map<String, Object> getBaseEnvironmentProperties() {
		return baseEnvironmentProperties;
	}

	/**
	 * Sets the base environment properties.
	 *
	 * @param baseEnvironmentProperties the base environment properties
	 */
	public void setBaseEnvironmentProperties(Map<String, Object> baseEnvironmentProperties) {
		this.baseEnvironmentProperties = baseEnvironmentProperties;
	}

	/**
	 * Returns the cache environment properties.
	 *
	 * @return the cache environment properties
	 */
	public boolean isCacheEnvironmentProperties() {
		return cacheEnvironmentProperties;
	}

	/**
	 * Sets the cache environment properties.
	 *
	 * @param cacheEnvironmentProperties the cache environment properties
	 */
	public void setCacheEnvironmentProperties(boolean cacheEnvironmentProperties) {
		this.cacheEnvironmentProperties = cacheEnvironmentProperties;
	}

	/**
	 * Returns the search base.
	 *
	 * @return the search base
	 */
	public String getSearchBase() {
		return searchBase;
	}

	/**
	 * Sets the search base.
	 *
	 * @param searchBase the search base
	 */
	public void setSearchBase(String searchBase) {
		this.searchBase = searchBase;
	}

	/**
	 * Returns the search filter.
	 *
	 * @return the search filter
	 */
	public String getSearchFilter() {
		return searchFilter;
	}

	/**
	 * Sets the search filter.
	 *
	 * @param searchFilter the search filter
	 */
	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}

	/**
	 * Returns the deref link flag.
	 *
	 * @return the deref link flag
	 */
	public boolean isDerefLinkFlag() {
		return derefLinkFlag;
	}

	/**
	 * Sets the deref link flag.
	 *
	 * @param derefLinkFlag the deref link flag
	 */
	public void setDerefLinkFlag(boolean derefLinkFlag) {
		this.derefLinkFlag = derefLinkFlag;
	}

	/**
	 * Returns the returning attrs.
	 *
	 * @return the returning attrs
	 */
	public String[] getReturningAttrs() {
		return returningAttrs;
	}

	/**
	 * Sets the returning attrs.
	 *
	 * @param returningAttrs the returning attrs
	 */
	public void setReturningAttrs(String[] returningAttrs) {
		this.returningAttrs = returningAttrs;
	}

	/**
	 * Returns the search subtree.
	 *
	 * @return the search subtree
	 */
	public boolean isSearchSubtree() {
		return searchSubtree;
	}

	/**
	 * Sets the search subtree.
	 *
	 * @param searchSubtree the search subtree
	 */
	public void setSearchSubtree(boolean searchSubtree) {
		this.searchSubtree = searchSubtree;
	}

	/**
	 * Returns the search time limit.
	 *
	 * @return the search time limit
	 */
	public int getSearchTimeLimit() {
		return searchTimeLimit;
	}

	/**
	 * Sets the search time limit.
	 *
	 * @param searchTimeLimit the search time limit
	 */
	public void setSearchTimeLimit(int searchTimeLimit) {
		this.searchTimeLimit = searchTimeLimit;
	}
	
	

	/**
	 * Returns the acl class id supported.
	 *
	 * @return the acl class id supported
	 */
	public boolean isAclClassIdSupported() {
		return aclClassIdSupported;
	}

	/**
	 * Sets the acl class id supported.
	 *
	 * @param aclClassIdSupported the acl class id supported
	 */
	public void setAclClassIdSupported(boolean aclClassIdSupported) {
		this.aclClassIdSupported = aclClassIdSupported;
	}

	/**
	 * Returns the hide user not found exceptions.
	 *
	 * @return the hide user not found exceptions
	 */
	public boolean isHideUserNotFoundExceptions() {
		return hideUserNotFoundExceptions;
	}

	/**
	 * Sets the hide user not found exceptions.
	 *
	 * @param hideUserNotFoundExceptions the hide user not found exceptions
	 */
	public void setHideUserNotFoundExceptions(boolean hideUserNotFoundExceptions) {
		this.hideUserNotFoundExceptions = hideUserNotFoundExceptions;
	}

	/**
	 * Returns the foreign keys in database.
	 *
	 * @return the foreign keys in database
	 */
	public boolean isForeignKeysInDatabase() {
		return foreignKeysInDatabase;
	}

	/**
	 * Returns the delete entry by object identity foreign key sql.
	 *
	 * @return the delete entry by object identity foreign key sql
	 */
	public String getDeleteEntryByObjectIdentityForeignKeySql() {
		return deleteEntryByObjectIdentityForeignKeySql;
	}

	/**
	 * Returns the delete object identity by primary key sql.
	 *
	 * @return the delete object identity by primary key sql
	 */
	public String getDeleteObjectIdentityByPrimaryKeySql() {
		return deleteObjectIdentityByPrimaryKeySql;
	}

	/**
	 * Returns the class identity query sql.
	 *
	 * @return the class identity query sql
	 */
	public String getClassIdentityQuerySql() {
		return classIdentityQuerySql;
	}

	/**
	 * Returns the sid identity query sql.
	 *
	 * @return the sid identity query sql
	 */
	public String getSidIdentityQuerySql() {
		return sidIdentityQuerySql;
	}

	/**
	 * Returns the insert class sql.
	 *
	 * @return the insert class sql
	 */
	public String getInsertClassSql() {
		return insertClassSql;
	}

	/**
	 * Returns the insert entry sql.
	 *
	 * @return the insert entry sql
	 */
	public String getInsertEntrySql() {
		return insertEntrySql;
	}

	/**
	 * Returns the insert object identity sql.
	 *
	 * @return the insert object identity sql
	 */
	public String getInsertObjectIdentitySql() {
		return insertObjectIdentitySql;
	}

	/**
	 * Returns the insert sid sql.
	 *
	 * @return the insert sid sql
	 */
	public String getInsertSidSql() {
		return insertSidSql;
	}

	/**
	 * Returns the select class primary key sql.
	 *
	 * @return the select class primary key sql
	 */
	public String getSelectClassPrimaryKeySql() {
		return selectClassPrimaryKeySql;
	}

	/**
	 * Returns the select object identity primary key sql.
	 *
	 * @return the select object identity primary key sql
	 */
	public String getSelectObjectIdentityPrimaryKeySql() {
		return selectObjectIdentityPrimaryKeySql;
	}

	/**
	 * Returns the select sid primary key sql.
	 *
	 * @return the select sid primary key sql
	 */
	public String getSelectSidPrimaryKeySql() {
		return selectSidPrimaryKeySql;
	}

	/**
	 * Returns the update object identity sql.
	 *
	 * @return the update object identity sql
	 */
	public String getUpdateObjectIdentitySql() {
		return updateObjectIdentitySql;
	}

	/**
	 * Sets the foreign keys in database.
	 *
	 * @param foreignKeysInDatabase the foreign keys in database
	 */
	public void setForeignKeysInDatabase(boolean foreignKeysInDatabase) {
		this.foreignKeysInDatabase = foreignKeysInDatabase;
	}

	/**
	 * Sets the delete entry by object identity foreign key sql.
	 *
	 * @param deleteEntryByObjectIdentityForeignKeySql the delete entry by object identity foreign key sql
	 */
	public void setDeleteEntryByObjectIdentityForeignKeySql(String deleteEntryByObjectIdentityForeignKeySql) {
		this.deleteEntryByObjectIdentityForeignKeySql = deleteEntryByObjectIdentityForeignKeySql;
	}

	/**
	 * Sets the delete object identity by primary key sql.
	 *
	 * @param deleteObjectIdentityByPrimaryKeySql the delete object identity by primary key sql
	 */
	public void setDeleteObjectIdentityByPrimaryKeySql(String deleteObjectIdentityByPrimaryKeySql) {
		this.deleteObjectIdentityByPrimaryKeySql = deleteObjectIdentityByPrimaryKeySql;
	}

	/**
	 * Sets the class identity query sql.
	 *
	 * @param classIdentityQuerySql the class identity query sql
	 */
	public void setClassIdentityQuerySql(String classIdentityQuerySql) {
		this.classIdentityQuerySql = classIdentityQuerySql;
	}

	/**
	 * Sets the sid identity query sql.
	 *
	 * @param sidIdentityQuerySql the sid identity query sql
	 */
	public void setSidIdentityQuerySql(String sidIdentityQuerySql) {
		this.sidIdentityQuerySql = sidIdentityQuerySql;
	}

	/**
	 * Sets the insert class sql.
	 *
	 * @param insertClassSql the insert class sql
	 */
	public void setInsertClassSql(String insertClassSql) {
		this.insertClassSql = insertClassSql;
	}

	/**
	 * Sets the insert entry sql.
	 *
	 * @param insertEntrySql the insert entry sql
	 */
	public void setInsertEntrySql(String insertEntrySql) {
		this.insertEntrySql = insertEntrySql;
	}

	/**
	 * Sets the insert object identity sql.
	 *
	 * @param insertObjectIdentitySql the insert object identity sql
	 */
	public void setInsertObjectIdentitySql(String insertObjectIdentitySql) {
		this.insertObjectIdentitySql = insertObjectIdentitySql;
	}

	/**
	 * Sets the insert sid sql.
	 *
	 * @param insertSidSql the insert sid sql
	 */
	public void setInsertSidSql(String insertSidSql) {
		this.insertSidSql = insertSidSql;
	}

	/**
	 * Sets the select class primary key sql.
	 *
	 * @param selectClassPrimaryKeySql the select class primary key sql
	 */
	public void setSelectClassPrimaryKeySql(String selectClassPrimaryKeySql) {
		this.selectClassPrimaryKeySql = selectClassPrimaryKeySql;
	}

	/**
	 * Sets the select object identity primary key sql.
	 *
	 * @param selectObjectIdentityPrimaryKeySql the select object identity primary key sql
	 */
	public void setSelectObjectIdentityPrimaryKeySql(String selectObjectIdentityPrimaryKeySql) {
		this.selectObjectIdentityPrimaryKeySql = selectObjectIdentityPrimaryKeySql;
	}

	/**
	 * Sets the select sid primary key sql.
	 *
	 * @param selectSidPrimaryKeySql the select sid primary key sql
	 */
	public void setSelectSidPrimaryKeySql(String selectSidPrimaryKeySql) {
		this.selectSidPrimaryKeySql = selectSidPrimaryKeySql;
	}

	/**
	 * Sets the update object identity sql.
	 *
	 * @param updateObjectIdentitySql the update object identity sql
	 */
	public void setUpdateObjectIdentitySql(String updateObjectIdentitySql) {
		this.updateObjectIdentitySql = updateObjectIdentitySql;
	}

}
