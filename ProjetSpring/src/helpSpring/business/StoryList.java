package helpSpring.business;

 import java.util.List;

public class StoryList {

        private List stories;
        
        public StoryList (){
        }
        public void setStories(List stories) {
                this.stories = stories;
        }
        public List getStories() {
        	System.out.println("inside: getStories");
                return stories;
        }
        public void test(){
        	System.out.println("affiche");
        	
        }
        
        
}