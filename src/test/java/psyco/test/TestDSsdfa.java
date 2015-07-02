package psyco.test;

import org.junit.Test;

/**
 * Created by lipeng on 15/7/1.
 */
public class TestDSsdfa {

    public void th() {
        assert 1==3;
        throw new RuntimeException("sdfsdf");
    }

    @Test
    public void catchh() {
        try {
            th();
            System.out.println("sdf");
        } catch (Exception e) {
            System.out.printf("sdfsdfsdfdsd");
            e.printStackTrace();
        }
        catch (Error e){
            System.out.println("fuck");
        }

    }
}
