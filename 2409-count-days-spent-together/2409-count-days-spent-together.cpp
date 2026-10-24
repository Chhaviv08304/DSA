
class Solution {
public:
    int countDaysTogether(string arriveAlice, string leaveAlice,
                          string arriveBob, string leaveBob) {
        string start = max(arriveAlice, arriveBob);
        string end = min(leaveAlice, leaveBob);

        if (start > end) return 0;

        int days[] = {31, 28, 31, 30, 31, 30,
                      31, 31, 30, 31, 30, 31};

        int s = stoi(start.substr(0, 2));
        int e = stoi(end.substr(0, 2));

        int startDay = stoi(start.substr(3, 2));
        int endDay = stoi(end.substr(3, 2));

        for (int i = 0; i < s - 1; i++)
            startDay += days[i];

        for (int i = 0; i < e - 1; i++)
            endDay += days[i];

        return endDay - startDay + 1;
    }
};
