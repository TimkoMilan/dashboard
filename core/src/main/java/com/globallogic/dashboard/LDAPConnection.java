package com.globallogic.dashboard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LDAPConnection {

    @Bean(name = "ldapTemplate")
    public LdapTemplate ldapTemplate() {
        LdapTemplate ldapTemplate = new LdapTemplate(ldapContextSource());
        return ldapTemplate;
    }

    @Bean(name = "contextSource")
    public LdapContextSource ldapContextSource() {

        String url = "ldap://172.17.114.6";
        String base = "DC=intranet,DC=demo,DC=com";

        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setUrl(url);
        ldapContextSource.setUserDn(
                "CN=Andrea Stefanova,OU=KSC,OU=Allusers,DC=synapse,DC=com");
        ldapContextSource.afterPropertiesSet();
        return ldapContextSource;

    }

}