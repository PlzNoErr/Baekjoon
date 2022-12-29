function solution(numlist, n) {
    let answer = [];
    let arr = [];
    numlist.forEach(num => {
        arr.push(num - n);
    });

    arr.sort((a, b) => {
        if (Math.abs(a) == Math.abs(b)) return b - a;
        else return Math.abs(a) - Math.abs(b);
    });

    arr.forEach(num => {
        answer.push(n + num);
    });

    return answer;
}
