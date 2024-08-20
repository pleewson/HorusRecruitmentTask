package pl.recruitment;

public class NormalBlock implements Block{

    private final String color;
    private final String material;

    public NormalBlock(String color, String material){
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getMaterial() {
        return this.material;
    }
}
