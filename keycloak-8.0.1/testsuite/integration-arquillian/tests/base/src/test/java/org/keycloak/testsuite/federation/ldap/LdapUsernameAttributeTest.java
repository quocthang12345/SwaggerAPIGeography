/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.keycloak.testsuite.federation.ldap;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.TargetsContainer;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.models.ModelDuplicateException;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.ldap.idm.model.LDAPObject;
import static org.keycloak.testsuite.arquillian.DeploymentTargetModifier.AUTH_SERVER_CURRENT;
import org.keycloak.testsuite.runonserver.RunOnServerDeployment;
import org.keycloak.testsuite.runonserver.RunOnServerException;
import org.keycloak.testsuite.util.LDAPRule;

/**
 *
 * @author rmartinc
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LdapUsernameAttributeTest extends AbstractLDAPTest {

    @ClassRule
    public static LDAPRule ldapRule = new LDAPRule();

    @Deployment
    @TargetsContainer(AUTH_SERVER_CURRENT)
    public static WebArchive deploy() {
        return RunOnServerDeployment.create(UserResource.class, AbstractLDAPTest.class)
                .addPackages(true,
                        "org.keycloak.testsuite",
                        "org.keycloak.testsuite.federation.ldap");
    }

    @Override
    protected LDAPRule getLDAPRule() {
        return ldapRule;
    }

    @Override
    protected void afterImportTestRealm() {
        testingClient.server().run(session -> {
            LDAPTestContext ctx = LDAPTestContext.init(session);
            RealmModel appRealm = ctx.getRealm();
            appRealm.setEditUsernameAllowed(true);
        });
    }

    @Test
    public void testUsernameChange() {
        // create a user johndow
        testingClient.server().run(session -> {
            LDAPTestContext ctx = LDAPTestContext.init(session);
            RealmModel appRealm = ctx.getRealm();
            UserModel john = session.users().addUser(appRealm, "johndow");
            john.setEmail("johndow@email.cz");
            john.setFirstName("johndow");
            john.setLastName("johndow");
        });
        // check it is there
        testingClient.server().run(session -> {
            LDAPTestContext ctx = LDAPTestContext.init(session);
            RealmModel appRealm = ctx.getRealm();
            UserModel john = session.users().getUserByUsername("johndow", appRealm);
            Assert.assertNotNull(john);
            Assert.assertNotNull(john.getFederationLink());
            Assert.assertEquals("johndow", john.getUsername());
            Assert.assertEquals("johndow@email.cz", john.getEmail());
            Assert.assertEquals("johndow", john.getFirstName());
            Assert.assertEquals("johndow", john.getLastName());
            LDAPObject johnLdap = ctx.getLdapProvider().loadLDAPUserByUsername(appRealm, "johndow");
            Assert.assertNotNull(johnLdap);
            Assert.assertEquals("johndow", johnLdap.getDn().getFirstRdnAttrValue());
        });
        // rename to johndow2
        testingClient.server().run(session -> {
            LDAPTestContext ctx = LDAPTestContext.init(session);
            RealmModel appRealm = ctx.getRealm();
            UserModel john = session.users().getUserByUsername("johndow", appRealm);
            john.setUsername("johndow2");
            john.setEmail("johndow2@email.cz");
            john.setFirstName("johndow2");
            john.setLastName("johndow2");
        });
        // check it is johndow2 and remove
        testingClient.server().run(session -> {
            LDAPTestContext ctx = LDAPTestContext.init(session);
            RealmModel appRealm = ctx.getRealm();
            Assert.assertNull(session.users().getUserByUsername("johndow", appRealm));
            UserModel john2 = session.users().getUserByUsername("johndow2", appRealm);
            Assert.assertNotNull(john2);
            Assert.assertNotNull(john2.getFederationLink());
            Assert.assertEquals("johndow2", john2.getUsername());
            Assert.assertEquals("johndow2@email.cz", john2.getEmail());
            Assert.assertEquals("johndow2", john2.getFirstName());
            Assert.assertEquals("johndow2", john2.getLastName());
            LDAPObject johnLdap2 = ctx.getLdapProvider().loadLDAPUserByUsername(appRealm, "johndow2");
            Assert.assertNotNull(johnLdap2);
            Assert.assertEquals("johndow2", johnLdap2.getDn().getFirstRdnAttrValue());

            session.users().removeUser(appRealm, john2);
            Assert.assertNull(session.users().getUserByUsername("johndow2", appRealm));
        });
    }

    @Test
    public void testUsernameChangeAlreadyExists() {
        // create a user johndow and johndow2
        testingClient.server().run(session -> {
            LDAPTestContext ctx = LDAPTestContext.init(session);
            RealmModel appRealm = ctx.getRealm();
            UserModel john = session.users().addUser(appRealm, "johndow");
            john.setEmail("johndow@email.cz");
            john.setFirstName("johndow");
            john.setLastName("johndow");
            UserModel john2 = session.users().addUser(appRealm, "johndow2");
            john.setEmail("johndow2@email.cz");
            john.setFirstName("johndow2");
            john.setLastName("johndow2");
        });
        // check they are there
        testingClient.server().run(session -> {
            LDAPTestContext ctx = LDAPTestContext.init(session);
            RealmModel appRealm = ctx.getRealm();
            UserModel john = session.users().getUserByUsername("johndow", appRealm);
            Assert.assertNotNull(john);
            Assert.assertNotNull(john.getFederationLink());
            UserModel john2 = session.users().getUserByUsername("johndow2", appRealm);
            Assert.assertNotNull(john2);
            Assert.assertNotNull(john2.getFederationLink());
        });
        // rename johndow to johndow2 => it should fail
        try {
             testingClient.server().run(session -> {
                 LDAPTestContext ctx = LDAPTestContext.init(session);
                 RealmModel appRealm = ctx.getRealm();
                 UserModel john = session.users().getUserByUsername("johndow", appRealm);
                 john.setUsername("johndow2");
             });
             Assert.assertFalse("Model exception is expected here, so it should not reach this point", true);
         } catch (RunOnServerException e) {
             Assert.assertTrue("Model exception is expected here but another error found", e.getCause() instanceof ModelDuplicateException);
             Assert.assertEquals(UserModel.USERNAME, ((ModelDuplicateException)e.getCause()).getDuplicateFieldName());
         }
        // remove both users
        testingClient.server().run(session -> {
            LDAPTestContext ctx = LDAPTestContext.init(session);
            RealmModel appRealm = ctx.getRealm();
            UserModel john = session.users().getUserByUsername("johndow", appRealm);
            Assert.assertNotNull(john);
            UserModel john2 = session.users().getUserByUsername("johndow2", appRealm);
            Assert.assertNotNull(john2);
            session.users().removeUser(appRealm, john);
            session.users().removeUser(appRealm, john2);
            Assert.assertNull(session.users().getUserByUsername("johndow", appRealm));
            Assert.assertNull(session.users().getUserByUsername("johndow2", appRealm));
        });
    }
}
