package com.sea_emperor.model;

import com.sea_emperor.configurations.CommonConfigurations;

public enum ItemCategory
{
    VEG {
        @Override
        public int getChefCount()
        {
            return CommonConfigurations.VEG_CHEF_COUNT;
        }
    },
    NON_VEG {
        @Override
        public int getChefCount()
        {
            return CommonConfigurations.NON_VEG_CHEF_COUNT;
        }
    },
    JUICE {
        @Override
        public int getChefCount()
        {
            return CommonConfigurations.JUICE_CHEF_COUNT;
        }
    },
    TEA {
        @Override
        public int getChefCount()
        {
            return CommonConfigurations.TEA_CHEF_COUNT;
        }
    },
    SNACKS {
        @Override
        public int getChefCount()
        {
            return CommonConfigurations.SNACKS_CHEF_COUNT;
        }
    };

    public abstract int getChefCount();

}
