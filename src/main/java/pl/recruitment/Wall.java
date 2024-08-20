package pl.recruitment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Informacja dla rekrutera:
 *
 * W ramach zadania rekrutacyjnego zaimplementowałem interfejsy oraz klasy zgodnie z wymaganiami.
 * Dodatkowo, w celu weryfikacji poprawności działania zaimplementowanych metod, stworzyłem kilka testów jednostkowych.
 * Testy jednostkowe zostały umieszczone w osobnych plikach testowych i obejmują różne scenariusze, takie jak:
 * - Wyszukiwanie bloków według koloru, zarówno istniejących, jak i nieistniejących.
 * - Wyszukiwanie bloków według materiału.
 * - Obsługa złożonych struktur bloków przy użyciu wzorca Composite.
 *
 * Dla celów testowych zaimplementowałem również klasy pomocnicze (NormalBlock i CompositeBlockImpl) w strukturze testów,
 * aby umożliwić pełne przetestowanie funkcjonalności.
 *
 * Mam nadzieję, że te informacje pomogą w ocenie mojego rozwiązania.
 * Miłego dnia!:)
 */

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }


    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks) {
            if (block != null && block.getMaterial() != null && block.getColor().equals(color)) {
                return Optional.of(block);
            }

            if (block instanceof CompositeBlock) {
                Optional<Block> foundBlock = new Wall(((CompositeBlock) block).getBlocks()).findBlockByColor(color);
                if (foundBlock.isPresent()) {
                    return foundBlock;
                }
            }
        }
        return Optional.empty();
    }


    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> outputList = new ArrayList<>();

        for (Block block : blocks) {
            if (block != null && block.getMaterial() != null && block.getMaterial().equals(material)) {
                outputList.add(block);
            }

            if (block instanceof CompositeBlock) {
                outputList.addAll(new Wall(((CompositeBlock) block).getBlocks()).findBlocksByMaterial(material));
            }
        }

        return outputList;
    }


    @Override
    public int count() {
        int counter = 0;

        for (Block block : blocks) {
            if (block != null) {
                counter++;
            }
            if (block instanceof CompositeBlock) {
                counter += new Wall(((CompositeBlock) block).getBlocks()).count();
            }
        }

        return counter;
    }
}
