package com.someco.actions.test;

import static org.junit.Assert.assertNotNull;

import org.alfresco.repo.nodelocator.NodeLocatorService;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.service.cmr.action.Action;
import org.alfresco.service.cmr.action.ActionService;
import org.alfresco.service.cmr.repository.NodeService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.someco.action.executer.DisableWebFlag;

public class DisableWebFlagActionTest {

    private static final String ADMIN_USER_NAME = "admin";

    static Logger log = Logger.getLogger(DisableWebFlagActionTest.class);

    @Autowired
    @Qualifier("NodeService")
    protected NodeService nodeService;

    @Autowired
    @Qualifier("ActionService")
    protected ActionService actionService;

    @Autowired
    @Qualifier("nodeLocatorService")
    protected NodeLocatorService nodeLocatorService;

    @Test
    public void testGetAction() {
    	AuthenticationUtil.setFullyAuthenticatedUser(ADMIN_USER_NAME);
    	Action action = actionService.createAction(DisableWebFlag.NAME);
    	assertNotNull(action);
    }

    /*
     * This test depends on the presence of the content tutorial repo AMP
     */
    /*
    @Test
    public void testExecuteAction() {
    	AuthenticationUtil.setFullyAuthenticatedUser(ADMIN_USER_NAME);
        NodeRef companyHome = nodeLocatorService.getNode(CompanyHomeNodeLocator.NAME, null, null);

        // assign name
        String name = "Move Replaced Action Test (" + System.currentTimeMillis() + ")";
        Map<QName, Serializable> contentProps = new HashMap<QName, Serializable>();
        contentProps.put(ContentModel.PROP_NAME, name);

        // create content node
        ChildAssociationRef association = nodeService.createNode(
        				companyHome,
                        ContentModel.ASSOC_CONTAINS,
                        QName.createQName(NamespaceService.CONTENT_MODEL_PREFIX, name),
                        ContentModel.TYPE_CONTENT,
                        contentProps
                        );

        NodeRef content = association.getChildRef();

    	Action action = actionService.createAction(DisableWebFlag.NAME);
    	action.setParameterValue(SetWebFlag.PARAM_ACTIVE, true);
    	actionService.executeAction(action, content);

    	nodeService.deleteNode(content);
    }
    */
}
