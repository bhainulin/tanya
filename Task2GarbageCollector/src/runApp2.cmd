@rem There are common args
set Task2Workspace=D:/eclipseLUNA/workspace/Task2GarbageCollector
set CP_ARGS=-classpath %Task2Workspace%/lib/log4j-1.2.17.jar;%Task2Workspace%/bin com/epam/jmp/gc/main/JavaApp2

set GC_ARGS_1=-XX:+UseSerialGC
set GC_ARGS_2=-XX:+UseParallelGC
set GC_ARGS_3=-XX:+UseParallelOldGC
set GC_ARGS_4=-XX:+UseConcMarkSweepGC
set GC_ARGS_5=-XX:+UseConcMarkSweepGC -XX:ConcGCThreads=2
set GC_ARGS_6=-XX:+UseParallelGC -XX:ParallelGCThreads=2
set GC_ARGS_7=-XX:+UseG1GC

@rem set MEM_ARGS_0=-Xms4m -Xmx16m -XX:NewSize=2m -XX:MaxNewSize=2m -XX:PermSize=12m -XX:MaxPermSize=18m
set MEM_ARGS_0=-Xms512m -Xmx512m -XX:NewSize=200m -XX:MaxNewSize=200m -XX:PermSize=400m -XX:MaxPermSize=400m 

set LOGS_ARGS_1=-XX:+PrintGCTimeStamps -Xloggc:%Task2Workspace%/GClogs2/gc_1.log -XX:+PrintGCDetails -XX:+PrintHeapAtGC
set LOGS_ARGS_2=-XX:+PrintGCTimeStamps -Xloggc:%Task2Workspace%/GClogs2/gc_2.log -XX:+PrintGCDetails -XX:+PrintHeapAtGC
set LOGS_ARGS_3=-XX:+PrintGCTimeStamps -Xloggc:%Task2Workspace%/GClogs2/gc_3.log -XX:+PrintGCDetails -XX:+PrintHeapAtGC
set LOGS_ARGS_4=-XX:+PrintGCTimeStamps -Xloggc:%Task2Workspace%/GClogs2/gc_4.log -XX:+PrintGCDetails -XX:+PrintHeapAtGC
set LOGS_ARGS_5=-XX:+PrintGCTimeStamps -Xloggc:%Task2Workspace%/GClogs2/gc_5.log -XX:+PrintGCDetails -XX:+PrintHeapAtGC
set LOGS_ARGS_6=-XX:+PrintGCTimeStamps -Xloggc:%Task2Workspace%/GClogs2/gc_6.log -XX:+PrintGCDetails -XX:+PrintHeapAtGC
set LOGS_ARGS_7=-XX:+PrintGCTimeStamps -Xloggc:%Task2Workspace%/GClogs2/gc_7.log -XX:+PrintGCDetails -XX:+PrintHeapAtGC

java %GC_ARGS_1% %MEM_ARGS_0% %LOGS_ARGS_1% %CP_ARGS% -%GC_ARGS_1%
java %GC_ARGS_2% %MEM_ARGS_0% %LOGS_ARGS_2% %CP_ARGS% -%GC_ARGS_2%
java %GC_ARGS_3% %MEM_ARGS_0% %LOGS_ARGS_3% %CP_ARGS% -%GC_ARGS_3%
java %GC_ARGS_4% %MEM_ARGS_0% %LOGS_ARGS_4% %CP_ARGS% -%GC_ARGS_4%
java %GC_ARGS_5% %MEM_ARGS_0% %LOGS_ARGS_5% %CP_ARGS% -%GC_ARGS_5%
java %GC_ARGS_6% %MEM_ARGS_0% %LOGS_ARGS_6% %CP_ARGS% -%GC_ARGS_6%
java %GC_ARGS_7% %MEM_ARGS_0% %LOGS_ARGS_7% %CP_ARGS% -%GC_ARGS_7%



