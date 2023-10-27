package model;

public class GlossaryModel {
    private String title;
    private GlossDivModel glossDiv;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GlossDivModel getGlossDiv() {
        return glossDiv;
    }

    public void setGlossDiv(GlossDivModel glossDiv) {
        this.glossDiv = glossDiv;
    }

    public static class GlossDivModel
    {
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }

        private boolean flag;
    }
}
