package pl.recruitment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class WallTest {

    private Wall wall;

    @BeforeEach
    void setUp() {
        Block block1 = new NormalBlock("brown", "wood");
        Block block2 = new NormalBlock("red", "brick");
        Block block3 = new CompositeBlockImpl("black", "metal",
                Arrays.asList(new NormalBlock("white", "paper"),
                new NormalBlock("yellow", "wood"))); //creating a new CompositeBlock that contains another "NormalBlocks"

        List<Block> blocks = new ArrayList<>();
        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);

        wall = new Wall(blocks);
    }


    @Test
    public void givenColor_whenFindBlockByColor_thenReturnIsCorrect() {
        Optional<Block> foundBlock = wall.findBlockByColor("red");

        assertTrue(foundBlock.isPresent());
        assertEquals("red", foundBlock.get().getColor());
    }


    @Test
    public void givenEmptyWall_whenFindBlockByColor_thenReturnEmpty() {
        Wall emptyWall = new Wall(new ArrayList<>());

        Optional<Block> foundBlock = emptyWall.findBlockByColor("red");

        assertFalse(foundBlock.isPresent());
    }


    @Test
    public void givenColor_whenFindBlockByColorThatAppearInCompositeBlock_thenReturnIsCorrect() {
        Optional<Block> foundBlock = wall.findBlockByColor("yellow");

        assertTrue(foundBlock.isPresent());
        assertEquals("yellow", foundBlock.get().getColor());
    }


    @Test
    public void givenNonExistingColor_whenFindBlockByColor_thenReturnEmpty() {
        Optional<Block> foundBlock = wall.findBlockByColor("orange");

        assertFalse(foundBlock.isPresent());
    }


    @Test
    public void givenMaterial_whenTestFindBlocksByMaterial_thenReturnIsCorrect() {
        List<Block> foundBlocks = wall.findBlocksByMaterial("wood");

        assertEquals(2, foundBlocks.size());
        assertEquals("wood", foundBlocks.get(0).getMaterial());
        assertEquals("wood", foundBlocks.get(1).getMaterial());
    }


    @Test
    public void givenNonExistingMaterial_whenTestFindBlocksByMaterial_thenReturnEmptyList() {
        List<Block> foundBlocks = wall.findBlocksByMaterial("plastic");

        assertTrue(foundBlocks.isEmpty());
    }


    @Test
    public void givenListWithBlocks_whenCount_thenResultIsCorrect() {
        int counter = wall.count();

        assertEquals(5, counter);
    }


    @Test
    public void givenEmptyList_whenCount_thenResultIsZero() {
        Wall emptyWall = new Wall(new ArrayList<>());

        int counter = emptyWall.count();

        assertEquals(0, counter);
    }
}