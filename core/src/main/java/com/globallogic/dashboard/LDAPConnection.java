package com.globallogic.dashboard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LDAPConnection {



    @Bean(name = "ldapTemplate")
    // @Scope("singleton")
    public LdapTemplate ldapTemplate() {
        LdapTemplate ldapTemplate = new LdapTemplate(ldapContextSource());
        return ldapTemplate;
    }

    @Bean(name = "contextSource")
    // @Scope("singleton")
    public LdapContextSource ldapContextSource() {

        String url = "ldap://127.0.0.1:389";
        String base = "DC=intranet,DC=demo,DC=com";

        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setUrl(url);
        ldapContextSource.setUserDn(
                "CN=Andrea Stefanova,OU=KSC,OU=Allusers,DC=synapse,DC=com");
        // lcs.setPooled(false);
        // lcs.setDirObjectFactory(DefaultDirObjectFactory.class);
        ldapContextSource.afterPropertiesSet();
        return ldapContextSource;
    }
}