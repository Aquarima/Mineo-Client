package com.exalow.mineo.util;

import com.exalow.mineo.views.AppController;
import com.exalow.mineo.views.FXMLView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ViewLoader implements ApplicationContextAware {

    @Autowired
    private static ApplicationContext context;

    public static <E extends Parent> E load(Class<E> type, AppController controller) {

        E parent = null;

        try {

            FXMLView annotation = controller.getClass()
                    .getAnnotation(FXMLView.class);

            FXMLLoader loader = new FXMLLoader(Resources.getResource(annotation.fxml()));
            loader.setController(controller);
            loader.setResources(context.getBean(MultiResourceBundle.class));
            parent = loader.load();

            List<String> stylesheets = parent.getStylesheets();
            stylesheets.add(annotation.css());

            String defaultStyle = "/assets/views/css/Default.css";

            if (!stylesheets.contains(defaultStyle)) {
                stylesheets.add(defaultStyle);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return parent;
    }

    public static Parent load(AppController controller) {
        return load(Parent.class, controller);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        ViewLoader.context = context;
    }
}
