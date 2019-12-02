package com.sea_emperor.chef;

import com.sea_emperor.model.ItemCategory;

public class ChefProvider
{
    Chef chef;

    public Chef getChef( ItemCategory itemCategory )
    {
        if (itemCategory == ItemCategory.NON_VEG) {
            chef = ChefCategory.INSTANCE.getNonVegChef();
        } else if (itemCategory == ItemCategory.VEG) {
            chef = ChefCategory.INSTANCE.getVegChef();
        } else if (itemCategory == ItemCategory.SNACKS) {
            chef = ChefCategory.INSTANCE.getSnacksChef();
        } else if (itemCategory == ItemCategory.TEA) {
            chef = ChefCategory.INSTANCE.getTeaChef();
        } else if (itemCategory == ItemCategory.JUICE) {
            chef = ChefCategory.INSTANCE.getJuiceChef();
        }

        return chef;
    }

    public enum ChefCategory
    {
        INSTANCE;

        NonVegChef nonVegChef;
        VegChef vegChef;
        JuiceChef juiceChef;
        SnacksChef snacksChef;
        TeaChef teaChef;

        public NonVegChef getNonVegChef()
        {
            if (this.nonVegChef == null) {
                nonVegChef = new NonVegChef();
            }
            return nonVegChef;
        }


        public VegChef getVegChef()
        {
            if (this.vegChef == null) {
                vegChef = new VegChef();
            }
            return vegChef;
        }

        public JuiceChef getJuiceChef()
        {
            if (this.juiceChef == null) {
                juiceChef = new JuiceChef();
            }
            return juiceChef;
        }

        public SnacksChef getSnacksChef()
        {
            if (this.snacksChef == null) {
                snacksChef = new SnacksChef();
            }
            return snacksChef;
        }


        public TeaChef getTeaChef()
        {
            if (this.teaChef == null) {
                teaChef = new TeaChef();
            }
            return teaChef;
        }

    }
}
