package inf112.skeleton.app.Cards;

import java.util.ArrayList;
import java.util.Collections;

public class CardStack implements ICardStack {

    private ArrayList<Card> currentCardStack;

    public CardStack() {
        currentCardStack = new ArrayList<>();
    }

    @Override
    public void initializeCardStack() {
        for (int a = 490; a <= 660; a+=10) {
            currentCardStack.add(new Card(CardType.MOVE_1_FORWARD, a, true));
        }
        for (int b = 670; b <= 780; b+=10) {
            currentCardStack.add(new Card(CardType.MOVE_2_FORWARD, b, true));
        }
        for (int c = 790; c <= 840; c+=10) {
            currentCardStack.add(new Card(CardType.MOVE_3_FORWARD, c, true));
        }
        for (int d = 430; d <= 480; d+=10) {
            currentCardStack.add(new Card(CardType.MOVE_1_BACKWARD, d, true));
        }
        for (int e = 70; e <= 410; e+=20) {
            currentCardStack.add(new Card(CardType.ROTATE_90_LEFT, e, true));
        }
        for (int f = 80; f <= 420; f+=20) {
            currentCardStack.add(new Card(CardType.ROTATE_90_RIGHT, f, true));
        }
        for (int g = 10; g <= 60; g+=10) {
            currentCardStack.add(new Card(CardType.ROTATE_180, g, true));
        }
    }

    @Override
    public void randomizeCardStack() {
        Collections.shuffle(currentCardStack);
    }

    @Override
    public Card getCardFromStack() {
        Card card = currentCardStack.get(0);
        currentCardStack.remove(0);
        return card;
    }

    @Override
    public void addCardToStack(Card card) {
        currentCardStack.add(card);
    }

    @Override
    public int size() {
        return currentCardStack.size();
    }
}
