package test;

import com.company.Card;
import com.company.ICard;
import com.company.Player;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class PlayerTest {
    private static Player player;
    private static final InputStream DEFAULT_STDIN = System.in;

    @AfterAll
    public static void rollbackChangesToStdin() {
        System.setIn(DEFAULT_STDIN);
    }

    @BeforeEach
    public void generatePlayer(){
        player = new Player("bla");
    }

    @Test
    void addToBank() {
        int money = 100;
        player.addToBank(money);
        Assertions.assertEquals(money, player.getBank());

        player.addToBank(money);
        Assertions.assertEquals(money*2, player.getBank());

        int negativeMoney = -100;
        player.addToBank(negativeMoney);
        player.addToBank(negativeMoney);
        player.addToBank(negativeMoney);
        Assertions.assertEquals(negativeMoney, player.getBank());
    }

    @Test
    void addCard() {
        List<ICard> list = new ArrayList<>();
        list.add(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        list.add(new Card(Card.Rank.JACK, Card.Suit.CLUBS));
        list.add(new Card(Card.Rank.QUEEN, Card.Suit.CLUBS));
        list.add(new Card(Card.Rank.KING, Card.Suit.CLUBS));
        list.add(new Card(Card.Rank.ACE, Card.Suit.SPADES));

        for(ICard card : list){
            player.addCard(card);
        }

        assertThat(list, is(player.getHand()));
        player.changeActiveHand();
        assertThat(new ArrayList<>(), is(player.getHand()));

        for(ICard card : list){
            player.addCard(card);
        }
        assertThat(list, is(player.getHand()));
    }

    @Test
    void splitHand() {
        //        Assertions.assertFalse(player.splitHand());
        System.setIn(new ByteArrayInputStream("70".getBytes()));
        player = new Player("bla");
        player.addToBank(333333);
        player.addCard(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        player.addCard(new Card(Card.Rank.ACE, Card.Suit.DIAMOND));

        System.setIn(new ByteArrayInputStream("\nY\n".getBytes()));

        Assertions.assertTrue(player.splitHand());

        player.eraseHands();


        player.addCard(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        player.addCard(new Card(Card.Rank.ACE, Card.Suit.DIAMOND));

        player.splitHand();
    }

    @Test
    void bet() {

        System.setIn(new ByteArrayInputStream("70".getBytes()));

        player = new Player("bla");
        player.addToBank(100);

        player.bet();

        Assertions.assertEquals(30, player.getBank());
    }

    @Test
    public void testBarCorrect() {
        System.setIn(new ByteArrayInputStream("7\n2\n3".getBytes()));
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextInt());
    }


    @Test
    void getValueOfHand() {
        Assertions.assertEquals(0, player.getValueOfHand());

        player.addCard(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        player.addCard(new Card(Card.Rank.ACE, Card.Suit.CLUBS));

        Assertions.assertEquals(12, player.getValueOfHand());

        player = new Player("bla");

        player.addCard(new Card(Card.Rank.ACE, Card.Suit.CLUBS));
        player.addCard(new Card(Card.Rank.JACK, Card.Suit.CLUBS));

        Assertions.assertEquals(21, player.getValueOfHand());
    }

}