/**
 * Entity class to present suggestions and their distances with original word
 */

package com.ihsinformatics.magicspells.api;

/**
 * @author owais.hussain@irdresearch.org
 *
 */
public class Suggestion {
    private String suggestedWord;
    private double distance;

    /**
     * Default constructor
     */
    public Suggestion() {
	setSuggestedWord("");
	setDistance(1);
    }

    /**
     * Constructor
     * 
     * @param suggestedWord
     *            suggestion against original word
     * @param distance
     *            distance (0-1) of suggested word from original word
     */
    public Suggestion(String suggestedWord, double distance) {
	setSuggestedWord(suggestedWord);
	setDistance(distance);
    }

    /**
     * Get suggested word
     * 
     * @return
     */
    public String getSuggestedWord() {
	return suggestedWord;
    }

    /**
     * Set suggested word
     * 
     * @param suggestedWord
     */
    public void setSuggestedWord(String suggestedWord) {
	this.suggestedWord = suggestedWord;
    }

    /**
     * Get distance of suggested word from the original word
     * 
     * @return
     */
    public double getDistance() {
	return distance;
    }

    /**
     * Set distance of suggested word from the original word
     * 
     * @param distance
     */
    public void setDistance(double distance) {
	this.distance = distance;
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	long temp;
	temp = Double.doubleToLongBits(distance);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result
		+ ((suggestedWord == null) ? 0 : suggestedWord.hashCode());
	return result;
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Suggestion other = (Suggestion) obj;
	if (Double.doubleToLongBits(distance) != Double
		.doubleToLongBits(other.distance))
	    return false;
	if (suggestedWord == null) {
	    if (other.suggestedWord != null)
		return false;
	} else if (!suggestedWord.equals(other.suggestedWord))
	    return false;
	return true;
    }

}
