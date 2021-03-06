class Solution:
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
        if not intervals : return 0
        intervals.sort(key = lambda a: a[1])
        end = intervals[0][1]
        count = 1
        for inter in intervals:
            if inter[0] >= end:
                count += 1
                end = inter[1]
        return len(intervals) - count
