package business;

public class Story {
        
        private String title;
        private String author;
        private String content;
        
        public Story () {
        }       
        public void setTitle(String title) {
                this.title = title;
        }
        public String getTitle() {
                return title;
        }
        public void setAuthor(String author) {
                this.author = author;
        }
        public String getAuthor() {
                return author;
        }
        public void setContent(String content) {
                this.content = content;
        }
        public String getContent() {
                return content;
        }
}