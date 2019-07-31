package baguchan.earthmobsmod.world.gen;

import baguchan.earthmobsmod.entity.MooBloomEntity;
import baguchan.earthmobsmod.handler.EarthBlocks;
import baguchan.earthmobsmod.handler.EarthEntitys;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class GoldenFlowerCircleFeature extends Feature<NoFeatureConfig> {
    public GoldenFlowerCircleFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49863_1_) {
        super(p_i49863_1_);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        BlockState flower = EarthBlocks.GOLDENBLOOM.getDefaultState();
        if (flower.isValidPosition(worldIn, pos)) {
            for (int i = -4; i <= 4; i++) {
                for (int j = -4; j <= 4; j++) {

                    float dist = (i * i) + (j * j);

                    if (dist < 9 || dist > 21) {
                        continue;
                    }

                    BlockPos fpos = pos.add(i, 0, j);

                    BlockPos posUp = fpos.up();

                    if (flower.isValidPosition(worldIn, fpos)) {
                        if (worldIn.getBlockState(fpos).isAir() || worldIn.getBlockState(fpos).getBlock().isIn(BlockTags.SMALL_FLOWERS)) {
                            worldIn.setBlockState(fpos, flower, 2);
                        } else if (worldIn.getBlockState(posUp).isAir() || worldIn.getBlockState(posUp).getBlock().isIn(BlockTags.SMALL_FLOWERS)) {
                            worldIn.setBlockState(posUp, flower, 2);
                        }
                        break;
                    }
                }
            }
            MooBloomEntity cowEntity = EarthEntitys.MOOBLOOM.create(worldIn.getWorld());
            cowEntity.setLocationAndAngles(pos.getX(), pos.getY(), pos.getZ(), 0.0F, 0.0F);
            cowEntity.setFlowerHome(pos);

            worldIn.addEntity(cowEntity);
            return true;
        }
        return false;
    }
}
