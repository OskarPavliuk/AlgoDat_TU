import java.util.*;

/**
 * The class {@code Bettelmann} simulated the card game 'Bettelmann'. You can construct objects
 * either by providing the piles of cards of the two players, or by requesting a shuffled
 * distribution of cards.
 */
public class Bettelmann {
    private Deque<Card> closedPile1;
    private Deque<Card> closedPile2;
    private int winner = -1;

    /**
     * Constructor which initializes both players with empty piles.
     */
    public Bettelmann() {
        closedPile1 = new LinkedList<>();
        closedPile2 = new LinkedList<>();
    }

    /**
     * Constructor which initializes both players with the provided piles of cards.
     *
     * @param pile1 pile of cards of player 1.
     * @param pile2 pile of cards of player 2.
     */
    public Bettelmann(Deque<Card> pile1, Deque<Card> pile2) {
        closedPile1 = pile1;
        closedPile2 = pile2;
    }

    /**
     * Returns the closed pile of player 1 (required for the tests).
     *
     * @return The closed pile of player 1.
     */
    public Deque<Card> getClosedPile1() {
        return closedPile1;
    }

    /**
     * Returns the closed pile of player 2 (required for the tests).
     *
     * @return The closed pile of player 2.
     */
    public Deque<Card> getClosedPile2() {
        return closedPile2;
    }

    /**
     * Play one round of the game. This includes drawing more cards, when both players
     * have drawn cards of the same rank. At the end of the round, the player with the
     * higher ranked card wins the trick, so all drawn cards from that round are added
     * to the bottom of her/his closed pile of cards.
     */

    /*
    Ich habe beschlossen, die Struktur zu nutzen, die wir bereits haben,
    und einfach die entsprechenden Methoden anzuwenden
     */
    public void playRound() {
        /*
        Da wir bereits geschlossene Decks haben, erstelle ich zunächst zwei offene Decks für zwei Spieler
         */
        Deque<Card> openPile1 = new LinkedList<>();
        Deque<Card> openPile2 = new LinkedList<>();

        /*
        Als nächstes erstelle ich ein Objekt der Card-Klasse,
        bei dem ich einfach ein Element von der Oberseite des geschlossenen Decks nehme
         */

        Card cardforfirstplayer = closedPile1.pollFirst();
        Card cardforsecondplayer = closedPile2.pollFirst();

        /*
        Und dann füge ich diese Karte zu meinem offenen Deck hinzu
         */
        openPile1.addLast(cardforfirstplayer);
        openPile2.addLast(cardforsecondplayer);

        /*
        Hier beginnt der Hauptteil des Kartenvergleichs. Hier betrachten wir den Teil,
        bei dem unsere Karten gleich sind. Und wenn sie gleich sind, müssen wir noch einmal
        eine Karte aus dem geschlossenen Deck nehmen und sie zum offenen hinzufügen,
        wie wir das schon früher oben gemacht haben
         */
        while (cardforfirstplayer.compareTo(cardforsecondplayer) == 0) {
        /*
        Es kann jedoch vorkommen, dass beispielsweise mehrere Karten gleich sind und
        beim Vergleich früher oder später jemandem die Karten ausgehen. Daher muss entschieden werden,
        wer gewonnen und wer dementsprechend verloren hat
         */
            if (closedPile1.isEmpty() && closedPile2.isEmpty()) {
                winner = 0;
                return;
            } else if (closedPile1.isEmpty()) {
                winner = 2;
                return;
            } else if (closedPile2.isEmpty()) {
                winner = 1;
                return;
            }
            cardforfirstplayer = closedPile1.pollFirst();
            cardforsecondplayer = closedPile2.pollFirst();
            openPile1.addLast(cardforfirstplayer);
            openPile2.addLast(cardforsecondplayer);
        }

        /*
        Hier wissen wir bereits genau, wessen Kartenwert gewonnen hat, und wir müssen diese Karten
        daher je nachdem, wer gewonnen hat, vom offenen Deck zum geschlossenen Deck hinzufügen.
        Wir müssen ausschließlich die Reihenfolge der Kartenaddition einprägen
         */
        if (cardforfirstplayer.compareTo(cardforsecondplayer) > 0) {
            closedPile1.addAll(openPile1);
            closedPile1.addAll(openPile2);
        } else if (cardforfirstplayer.compareTo(cardforsecondplayer) < 0) {
            closedPile2.addAll(openPile2);
            closedPile2.addAll(openPile1);
        }

        /*
        Und hier entscheiden wir bereits, wer gewonnen hat und ändern den Gewinner entsprechend zu dem,
        den wir brauchen. Wenn beide Decks leer sind, ist es ein Unentschieden
         */
        if (closedPile1.isEmpty() && closedPile2.isEmpty()) {
            winner = 0;
        } else if (closedPile1.isEmpty()) {
            winner = 2;
        } else if (closedPile2.isEmpty()) {
            winner = 1;
        }
    }

    /**
     * Returns the winner of the game after the end, or -1 during the game.
     *
     * @return the winner of game (1 or 2), or -1 while the game is ongoing.
     */
    public int getWinner() {
        return winner;
    }

    /**
     * Deal the given deck of cards alternately to the two players.
     * Side effect: The deck is empty after calling this method.
     *
     * @param deck The deck of cards that is distributed to the players.
     */
    public void distributeCards(Stack<Card> deck) {
        closedPile1.clear();
        closedPile2.clear();
        // use addFirst() because the last distributed card should be drawn first
        while (!deck.isEmpty()) {
            Card card = deck.pop();
            closedPile1.addFirst(card);
            if (!deck.isEmpty()) {
                card = deck.pop();
                closedPile2.addFirst(card);
            }
        }
    }

    /**
     * Shuffle a deck of cards and distribute it evenly to the two players.
     */
    public void distributeCards() {
        Stack<Card> deck = new Stack<>();
        for (int i = 0; i < Card.nCards; i++) {
            deck.add(new Card(i));
        }
        Collections.shuffle(deck);
        distributeCards(deck);
    }

    /**
     * Returns a String representation of closed piles of cards of the two players.
     *
     * @return String representation of the state of the game.
     */
    @Override
    public String toString() {
        return "Player 1: " + closedPile1 + "\nPlayer 2: " + closedPile2;
    }
}
