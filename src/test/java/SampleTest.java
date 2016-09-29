import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import model.Sample;
import xp.wheel.easydao.EasyDao;
import config.Config;

/**
 * @author xp
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Config.class})
@ActiveProfiles("test")
public class SampleTest {

    @Autowired
    private EasyDao<Sample> sampleDao;

    @Test
    public void test() {
        Sample sample = new Sample();
        sample.setName("test");
        Assert.assertEquals(1, sampleDao.insert(sample).intValue());
    }

}
