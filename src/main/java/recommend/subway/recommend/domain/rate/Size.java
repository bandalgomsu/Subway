package recommend.subway.recommend.domain.rate;

public class Size {
    private final int size;

    public Size(int size) {
        this.size = standard(size);
    }

    private int standard(int size){
        if(size > 10){
            return 3;
        }

        if (size > 5){
            return 2;
        }

        return 1;
    }

    public int getSize() {
        return size;
    }
}
