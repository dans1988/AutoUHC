package pl.dans.plugins.autouhc;

public class TimerConfig {

    private int pvpTime;
    private int permadayTime;
    private int meetupTime;
    private String startupCommand;
    private String delayedCommand;

    private TimerConfig(Builder builder) {
        this.pvpTime = builder.pvpTime;
        this.permadayTime = builder.permadayTime;
        this.meetupTime = builder.meetupTime;
        this.startupCommand = builder.startupCommand;
        this.delayedCommand = builder.delayedCommand;
    }

    public int getPvpTime() {
        return pvpTime;
    }

    public void setPvpTime(int pvpTime) {
        this.pvpTime = pvpTime;
    }

    public int getPermadayTime() {
        return permadayTime;
    }

    public void setPermadayTime(int permadayTime) {
        this.permadayTime = permadayTime;
    }

    public int getMeetupTime() {
        return meetupTime;
    }

    public void setMeetupTime(int meetupTime) {
        this.meetupTime = meetupTime;
    }

    public String getStartupCommand() {
        return startupCommand;
    }

    public void setStartupCommand(String startupCommand) {
        this.startupCommand = startupCommand;
    }

    public String getDelayedCommand() {
        return delayedCommand;
    }

    public void setDelayedCommand(String delayedCommand) {
        this.delayedCommand = delayedCommand;
    }

    public static class Builder {
        private int pvpTime;
        private int permadayTime;
        private int meetupTime;
        private String startupCommand;
        private String delayedCommand;

        public Builder setPvpTime(int pvpTime) {
            this.pvpTime = pvpTime;
            return this;
        }

        public Builder setPermadayTime(int permadayTime) {
            this.permadayTime = permadayTime;
            return this;
        }

        public Builder setMeetupTime(int meetupTime) {
            this.meetupTime = meetupTime;
            return this;
        }

        public Builder setStartupCommand(String startupCommand) {
            this.startupCommand = startupCommand;
            return this;
        }

        public Builder setDelayedCommand(String delayedCommand) {
            this.delayedCommand = delayedCommand;
            return this;
        }
        
        public TimerConfig build() {
            return new TimerConfig(this);
        }
        
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }

}
