package pl.recruitment;

import java.util.List;

public class CompositeBlockImpl implements CompositeBlock {

    public CompositeBlockImpl(String color, String material, List<Block> blocks) {
        this.color = color;
        this.material = material;
        this.blocks = blocks;
    }

    private final List<Block> blocks;
    private final String color;
    private final String material;

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getMaterial() {
        return this.material;
    }

    @Override
    public List<Block> getBlocks() {
        return this.blocks;
    }
}
