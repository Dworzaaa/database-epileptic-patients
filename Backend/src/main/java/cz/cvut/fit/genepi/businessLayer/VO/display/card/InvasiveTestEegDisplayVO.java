package cz.cvut.fit.genepi.businessLayer.VO.display.card;

/**
 * Created by Jan on 25.1.14.
 */
public class InvasiveTestEegDisplayVO {

    private int id;

    private String date;

    private boolean done;

    private int intracranialElectrodes;

    private String localizationIntracranialElectrodes;

    private int invasiveEegSlow;

    private int invasiveEegInterictalSpikes;

    private String localizationInvasiveEegInterictalSpikes;

    private boolean invasiveEegStatusEpilepticus;

    private int invasiveIctalEegPatterns;

    private int localizationInvasiveIctalEegPatterns;

    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getIntracranialElectrodes() {
        return intracranialElectrodes;
    }

    public void setIntracranialElectrodes(int intracranialElectrodes) {
        this.intracranialElectrodes = intracranialElectrodes;
    }

    public String getLocalizationIntracranialElectrodes() {
        return localizationIntracranialElectrodes;
    }

    public void setLocalizationIntracranialElectrodes(String localizationIntracranialElectrodes) {
        this.localizationIntracranialElectrodes = localizationIntracranialElectrodes;
    }

    public int getInvasiveEegSlow() {
        return invasiveEegSlow;
    }

    public void setInvasiveEegSlow(int invasiveEegSlow) {
        this.invasiveEegSlow = invasiveEegSlow;
    }

    public int getInvasiveEegInterictalSpikes() {
        return invasiveEegInterictalSpikes;
    }

    public void setInvasiveEegInterictalSpikes(int invasiveEegInterictalSpikes) {
        this.invasiveEegInterictalSpikes = invasiveEegInterictalSpikes;
    }

    public String getLocalizationInvasiveEegInterictalSpikes() {
        return localizationInvasiveEegInterictalSpikes;
    }

    public void setLocalizationInvasiveEegInterictalSpikes(String localizationInvasiveEegInterictalSpikes) {
        this.localizationInvasiveEegInterictalSpikes = localizationInvasiveEegInterictalSpikes;
    }

    public boolean isInvasiveEegStatusEpilepticus() {
        return invasiveEegStatusEpilepticus;
    }

    public void setInvasiveEegStatusEpilepticus(boolean invasiveEegStatusEpilepticus) {
        this.invasiveEegStatusEpilepticus = invasiveEegStatusEpilepticus;
    }

    public int getInvasiveIctalEegPatterns() {
        return invasiveIctalEegPatterns;
    }

    public void setInvasiveIctalEegPatterns(int invasiveIctalEegPatterns) {
        this.invasiveIctalEegPatterns = invasiveIctalEegPatterns;
    }

    public int getLocalizationInvasiveIctalEegPatterns() {
        return localizationInvasiveIctalEegPatterns;
    }

    public void setLocalizationInvasiveIctalEegPatterns(int localizationInvasiveIctalEegPatterns) {
        this.localizationInvasiveIctalEegPatterns = localizationInvasiveIctalEegPatterns;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
