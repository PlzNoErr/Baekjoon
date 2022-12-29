function solution(array) {
    let arr = new Array(1000).fill(0);

    array.forEach(num => {
        arr[num]++;
    });

    let max = 0;
    arr.forEach(num => {
        max = Math.max(max, num);
    });

    let check = 0;
    let idx;

    for (let i = 0; i < arr.length; i++) {
        if (arr[i] === max) {
            idx = i;
            check++;
        }
    }
    ;

    return check === 1 ? idx : -1;
}