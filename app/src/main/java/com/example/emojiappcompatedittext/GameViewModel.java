//package com.example.emojiappcompatedittext;
//
//import androidx.lifecycle.MediatorLiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//import java.util.Random;
//
//// MYCODEADDED: VIEWMODEL FOR THE MAINACTIVITY: SPECIFICALLY, A VIEWMODEL FOR THE ANSWER (RADIO BUTTON) OPTIONS
//public class GameViewModel extends ViewModel {
//
//    private final DataRepository mRepository;
//
//    // MYCODEADDED: THIS IS THE RANDOM LIST OF SMILEYS USES IN ANSWER OPTIONS THE MAINACTIVITY
//    private LiveData<List<Smiley>> mSmileys = null;
//
//
//    private final MediatorLiveData<List<Smiley>> mSmileysMediator = new MediatorLiveData<>();
//
//    // CURRENT QUESTION SMILEY
//    private final MutableLiveData<Smiley> mCurrentSmiley = new MutableLiveData<>();
//
//    // RESULT OBJECT USED TO UPDATE THE RESULT TETVIEW
//    private final MutableLiveData<Result> mResult = new MutableLiveData<>();
//
//    private int mCorrectScore = 0;
//    private int mIncorrectScore = 0;
//    // MYCODEADDED: SETS THE LIMIT OF POSSIBLE CHOICES FOR ANSWERS
//    // THIS IS INITIALIZED IN THE GAMEVIEWMODELFACTORY
//    // CAN BE EDITED BY THE USER IN THE SETTINGS: NEED TO MAKE SURE WHEN SETTINGS ARE UPDATED, THAT THIS VARIABLE IS UPDATED AS WELL
//    private int mLimitSmiley;
//
//    public GameViewModel(DataRepository repository, int answerLimit) {
//        this.mRepository = repository;
//        this.mLimitSmiley = answerLimit;
//    }
//
//    // THINK I NEED TO CALL THIS METHOD IN MAINACTIVITY TO FIRST SETUP THE GAME
//    /**
//     * Load Smileys from the database and set up a game round.
//     *
//     * @return observable list data of Smileys
//     */
//    public MediatorLiveData<List<Smiley>> setUpGame() {
//        if (mSmileys == null) {
//            mSmileysMediator.addSource(loadSmileys(), mSmileysMediator::setValue);
//        }
//
//        return mSmileysMediator;
//    }
//
//    /**
//     * Rests the game with new random Smileys.
//     */
//    public void resetGame() {
//        mSmileysMediator.removeSource(mSmileys);
//        mSmileysMediator.addSource(loadSmileys(), mSmileysMediator::setValue);
//
//        mCorrectScore = 0;
//        mIncorrectScore = 0;
//    }
//
//    public MutableLiveData<Smiley> getCurrentAnswer() {
//        return mCurrentSmiley;
//    }
//
//    // USED TO GET A RANDOM LIST OF SMILEYS GIVEN THE LIMIT OF ANSWERS
//    private LiveData<List<Smiley>> loadSmileys() {
//        mSmileys = mRepository.getRandomSmileys(mLimitSmiley);
//        return mSmileys;
//    }
//
//    /**
//     * Set up a round for the game.
//     */
//    public void startNewGameRound() {
//        if (getTotalScore() == 3) {
//            return;
//        }
//
//        Random random = new Random();
//        Smiley previous = mCurrentSmiley.getValue();
//        List<Smiley> smileys = mSmileys.getValue();
//
//        if (smileys == null || smileys.size() == 0) {
//            return;
//        }
//        int anInt = random.nextInt(smileys.size());
//        Smiley smiley = smileys.get(anInt);
//
//        if (previous != null && smileys.size() > 1 && !smiley.getName().isEmpty() &&
//                previous.getName().equals(smiley.getName())) {
//            startNewGameRound();
//            return;
//        }
//
//
//        // SETS THE QUESTION SMILEY TO A NEW RANDOM SMILEY
//        mCurrentSmiley.setValue(smiley);
//    }
//
//    public MutableLiveData<Result> getResults() {
//        return mResult;
//    }
//
//    /**
//     * Check the answer and determine end of three round game or start new round.
//     *
//     * @param answer an answer string from AnswersView
//     */
//    public void updateResult(String answer) {
//        Smiley smiley = mCurrentSmiley.getValue();
//        if (smiley == null) {
//            return;
//        }
//
//        Result result;
//        if (answer.equals(smiley.getCode())) {
//            increaseCorrectScore();
//            result = new Result(R.color.colorCorrect, R.string.correct_answer);
//        } else {
//            increaseIncorrectScore();
//            result = new Result(R.color.colorWrong, R.string.wrong_answer);
//        }
//
//        if (getTotalScore() != 3) {
//            startNewGameRound();
//            mResult.setValue(result);
//        } else {
//            goodGame();
//        }
//    }
//
//    /**
//     * Sets win or lose result.
//     */
//    private void goodGame() {
//        Result result;
//        if (mCorrectScore == 3) {
//            result = new Result(R.color.colorCorrect, R.string.win_game, false);
//        } else if (mCorrectScore == 2) {
//            result = new Result(R.color.colorAlmost, R.string.maybe_game, false);
//        } else {
//            result = new Result(R.color.colorWrong, R.string.lose_game, false);
//        }
//        mResult.setValue(result);
//    }
//
//    private void increaseCorrectScore() {
//        this.mCorrectScore += 1;
//    }
//
//    private void increaseIncorrectScore() {
//        this.mIncorrectScore += 1;
//    }
//
//    private int getTotalScore() {
//        return mCorrectScore + mIncorrectScore;
//    }
//
//}
//
