package com.example.game.activities;

/**
 * This class generates a random number
 */
class RandomNumberGenerator {

    private int randomNumber;
    private int minNumber;
    private int maxNumber;

    /**
     * Create a RandomNumberGenerator with a lower bound of minNumber and upper bound of maxNumber
     * @param minNumber - the lower bound of the random number
     * @param maxNumber - the upper bound of the random number
     */
    RandomNumberGenerator(int minNumber, int maxNumber){
        this.minNumber = minNumber;
        this.maxNumber = maxNumber;
    }

    /**
     *Return the random number
     * @return the random number
     */
    int getRandomNumber() {
        return randomNumber;
    }

    /**
     *Generate a random number
     */
    void generateRandomNumber(){
        randomNumber = (int) (Math.random() * ((maxNumber - minNumber) + 1)) + 1;
        // fix down casting
    }
}
