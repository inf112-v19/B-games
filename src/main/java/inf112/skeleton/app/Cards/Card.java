package inf112.skeleton.app.Cards;

public class Card implements ICard {

    private CardType type;
    private int priority;
    private boolean unlockedStatus;

    public Card(CardType type, int priority, boolean unlockedStatus) {
        this.type = type;
        this.priority = priority;
        this.unlockedStatus = unlockedStatus;
    }

    @Override
    public CardType getType() {
        return type;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public boolean getUnlockedStatus() {
        return unlockedStatus;
    }

    @Override
    public void setUnlocked() {
        unlockedStatus = true;
    }

    @Override
    public void setLocked() {
        unlockedStatus = false;
    }
}
