package com.learn.springboot.exception.model;

class Meta {
    private int page;
    private int size;
    private int total;
    private boolean hasNextPage;
    private boolean hasPreviousPage;

    public Meta(int page, int size, int total) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.hasNextPage = page * size < total;
        this.hasPreviousPage = page > 1;
    }

    // getters and setters
}
