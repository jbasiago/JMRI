package jmri.jmrit.operations.locations.tools;

import java.awt.GraphicsEnvironment;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.jupiter.api.Test;

import jmri.InstanceManager;
import jmri.jmrit.operations.OperationsTestCase;
import jmri.jmrit.operations.locations.*;
import jmri.jmrit.operations.locations.gui.LocationEditFrame;
import jmri.util.JUnitOperationsUtil;
import jmri.util.JUnitUtil;
import jmri.util.swing.JemmyUtil;

/**
 *
 * @author Paul Bender Copyright (C) 2017
 */
public class ChangeTracksFrameTest extends OperationsTestCase {

    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        
        JUnitOperationsUtil.initOperationsData();
        LocationManager lmanager = InstanceManager.getDefault(LocationManager.class);
        Location loc = lmanager.getLocationByName("North Industries");
        Assert.assertNotNull("exists", loc);
        
        LocationEditFrame f = new LocationEditFrame(loc);
        ChangeTracksFrame t = new ChangeTracksFrame(f);
        Assert.assertNotNull("exists", t);
        JUnitUtil.dispose(t);
        JUnitUtil.dispose(f);

    }
    
    @Test
    public void testFrameButtons() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());

        JUnitOperationsUtil.initOperationsData();
        LocationManager lmanager = InstanceManager.getDefault(LocationManager.class);
        Location loc = lmanager.getLocationByName("North Industries");
        Assert.assertNotNull("exists", loc);

        LocationEditFrame lef = new LocationEditFrame(loc);
        ChangeTracksFrame ctf = new ChangeTracksFrame(lef);
        Assert.assertNotNull("exists", ctf);
               
        // confirm default
        Assert.assertTrue(loc.hasYards());
        
        JemmyUtil.enterClickAndLeave(ctf.spurRadioButton);
        JemmyUtil.enterClickAndLeave(ctf.saveButton);
        
        // confirm change
        Assert.assertFalse(loc.hasYards());
        
        JUnitUtil.dispose(ctf);
        JUnitUtil.dispose(lef);
    }
    
    @Test
    public void testCloseWindowOnSave() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        JUnitOperationsUtil.initOperationsData();
        LocationManager lmanager = InstanceManager.getDefault(LocationManager.class);
        Location loc = lmanager.getLocationByName("North Industries");
        LocationEditFrame lef = new LocationEditFrame(loc);
        ChangeTracksFrame f = new ChangeTracksFrame(lef);
        JUnitOperationsUtil.testCloseWindowOnSave(f.getTitle());
    }


    // private final static Logger log = LoggerFactory.getLogger(ChangeTracksFrameTest.class);
}
