package pollub.myplanszeo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MyPlanszeoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyPlanszeoApplication.class, args);
    }

}
