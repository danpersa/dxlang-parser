package io.dx.generator.domain;

/**
 * @author  dpersa
 */
public class Block {

    private static final Block EMPTY_BLOCK = new EmtpyBlock();

    public static Block emptyBlock() {
        return EMPTY_BLOCK;
    }

    private static class EmtpyBlock extends Block { }
}
