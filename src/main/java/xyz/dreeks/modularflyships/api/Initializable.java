package xyz.dreeks.modularflyships.api;

import java.util.ArrayList;

public abstract class Initializable<T> {

    public void init() {
        for (T curr : this.getElements()) {
            if (curr instanceof IRegistrable) {
                IRegistrable registrable = (IRegistrable)curr;
                registrable.register();
            }
        }
    }

    protected abstract ArrayList<T> getElements();

}
