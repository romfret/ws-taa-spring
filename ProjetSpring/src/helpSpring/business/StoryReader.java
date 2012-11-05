package helpSpring.business;


import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class StoryReader {
        
        public static void main(String args[]) {
                ApplicationContext beanFactory = new ClassPathXmlApplicationContext("story_configuration.xml");
                StoryList storyList = (StoryList) beanFactory.getBean("business.StoryList");
                List myStories = storyList.getStories();
                for (int i = 0; i < myStories.size(); i++) {
                        Story currentStory = (Story) myStories.get(i);
                        System.out.println("\"" + currentStory.getTitle() + "\" by "
                                        + currentStory.getAuthor() + ":");
                        System.out.println(currentStory.getContent());
                        System.out.println();
                        
                }
                storyList.test();
        }
        
}