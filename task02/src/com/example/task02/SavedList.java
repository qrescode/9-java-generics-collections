package com.example.task02;

import java.io.*;
import java.util.ArrayList;
import java.util.AbstractList;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private final File file;
    private final ArrayList<E> data = new ArrayList<>();

    public SavedList(File file) {
        this.file = file;
        load();
    }

    private void load() {
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            data.addAll((ArrayList<E>) ois.readObject());
        } catch (Exception ignored) {}
    }

    private void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public E get(int index) {
        return data.get(index);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public E set(int index, E element) {
        E old = data.set(index, element);
        save();
        return old;
    }

    @Override
    public void add(int index, E element) {
        data.add(index, element);
        save();
    }

    @Override
    public E remove(int index) {
        E old = data.remove(index);
        save();
        return old;
    }
}