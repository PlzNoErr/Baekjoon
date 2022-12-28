function solution(my_str, n) {
    let answer = [];
    let count = 0;
    while (true) {
        if (count + n >= my_str.length) {
            answer.push(my_str.substring(count));
            break;
        }
        answer.push(my_str.substring(count, count + n));
        count += n;
    }

    return answer;
}
