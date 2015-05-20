@rem There are common args
set Task2Workspace=D:/eclipseLUNA/workspace/Task2GarbageCollector
set CP_ARGS=-classpath %Task2Workspace%/lib/log4j-1.2.17.jar;%Task2Workspace%/bin com/epam/jmp/gc/main/JavaApp

@rem Task 1: Serial Collector
set GC_ARGS_1=-XX:+UseSerialGC
set MEM_ARGS_1=-Xms6m -Xmx18m -XX:NewSize=2m -XX:MaxNewSize=2m -XX:PermSize=20m -XX:MaxPermSize=30m

@rem Task 2: Parallel Collector 
set GC_ARGS_2=-XX:+UseParallelGC
set MEM_ARGS_2=-Xms3m -Xmx12m -XX:NewSize=1m -XX:MaxNewSize=1m -XX:PermSize=20m -XX:MaxPermSize=20m

@rem Task 3: Parallel Old Collector
set GC_ARGS_3=-XX:+UseParallelOldGC
set MEM_ARGS_3=-Xms9m -Xmx18m -XX:NewSize=3m -XX:MaxNewSize=3m -XX:PermSize=40m -XX:MaxPermSize=40m

@rem Task 4: Concurrent Mark Sweep (CMS) Collector
set GC_ARGS_4=-XX:+UseConcMarkSweepGC
set MEM_ARGS_4=-Xms6m -Xmx18m -XX:NewSize=2m -XX:MaxNewSize=2m -XX:PermSize=20m -XX:MaxPermSize=30m

@rem Task 5: Concurrent Mark Sweep (CMS) Collector with 2 Parallel CMS Threads
set GC_ARGS_5=-XX:+UseConcMarkSweepGC -XX:ConcGCThreads=2
set MEM_ARGS_5=-Xms2m -Xmx18m -XX:NewSize=1m -XX:MaxNewSize=1m -XX:PermSize=24m -XX:MaxPermSize=36m

@rem Task 6: Parallel Collector with 2 Parallel CMS Threads
set GC_ARGS_6=-XX:+UseParallelGC -XX:ParallelGCThreads=2
set MEM_ARGS_6=-Xms4m -Xmx16m -XX:NewSize=3m -XX:MaxNewSize=3m -XX:PermSize=24m -XX:MaxPermSize=32m

@rem Task 7: G1 Garbage Collector
set GC_ARGS_7=-XX:+UseG1GC
set MEM_ARGS_7=-Xms4m -Xmx16m -XX:NewSize=2m -XX:MaxNewSize=2m -XX:PermSize=12m -XX:MaxPermSize=18m

@rem java %GC_ARGS_1% %MEM_ARGS_1% %CP_ARGS% -%GC_ARGS_1%
@rem java %GC_ARGS_2% %MEM_ARGS_2% %CP_ARGS% -%GC_ARGS_2%
@rem java %GC_ARGS_3% %MEM_ARGS_3% %CP_ARGS% -%GC_ARGS_3%
@rem java %GC_ARGS_4% %MEM_ARGS_4% %CP_ARGS% -%GC_ARGS_4%
@rem java %GC_ARGS_5% %MEM_ARGS_5% %CP_ARGS% -%GC_ARGS_5%
@rem java %GC_ARGS_6% %MEM_ARGS_6% %CP_ARGS% -%GC_ARGS_6%
@rem java %GC_ARGS_7% %MEM_ARGS_7% %CP_ARGS% -%GC_ARGS_7%


set MEM_ARGS_0=-Xms4m -Xmx16m -XX:NewSize=2m -XX:MaxNewSize=2m -XX:PermSize=12m -XX:MaxPermSize=18m

set LOGS_ARGS_1=-XX:+PrintGCTimeStamps -Xloggc:%Task2Workspace%/GClogs/gc_1.log -XX:+PrintGCDetails -XX:+PrintHeapAtGC
set LOGS_ARGS_2=-XX:+PrintGCTimeStamps -Xloggc:%Task2Workspace%/GClogs/gc_2.log -XX:+PrintGCDetails -XX:+PrintHeapAtGC
set LOGS_ARGS_3=-XX:+PrintGCTimeStamps -Xloggc:%Task2Workspace%/GClogs/gc_3.log -XX:+PrintGCDetails -XX:+PrintHeapAtGC
set LOGS_ARGS_4=-XX:+PrintGCTimeStamps -Xloggc:%Task2Workspace%/GClogs/gc_4.log -XX:+PrintGCDetails -XX:+PrintHeapAtGC
set LOGS_ARGS_5=-XX:+PrintGCTimeStamps -Xloggc:%Task2Workspace%/GClogs/gc_5.log -XX:+PrintGCDetails -XX:+PrintHeapAtGC
set LOGS_ARGS_6=-XX:+PrintGCTimeStamps -Xloggc:%Task2Workspace%/GClogs/gc_6.log -XX:+PrintGCDetails -XX:+PrintHeapAtGC
set LOGS_ARGS_7=-XX:+PrintGCTimeStamps -Xloggc:%Task2Workspace%/GClogs/gc_7.log -XX:+PrintGCDetails -XX:+PrintHeapAtGC

java %GC_ARGS_1% %MEM_ARGS_0% %LOGS_ARGS_1% %CP_ARGS% -%GC_ARGS_1%
java %GC_ARGS_2% %MEM_ARGS_0% %LOGS_ARGS_2% %CP_ARGS% -%GC_ARGS_2%
java %GC_ARGS_3% %MEM_ARGS_0% %LOGS_ARGS_3% %CP_ARGS% -%GC_ARGS_3%
java %GC_ARGS_4% %MEM_ARGS_0% %LOGS_ARGS_4% %CP_ARGS% -%GC_ARGS_4%
java %GC_ARGS_5% %MEM_ARGS_0% %LOGS_ARGS_5% %CP_ARGS% -%GC_ARGS_5%
java %GC_ARGS_6% %MEM_ARGS_0% %LOGS_ARGS_6% %CP_ARGS% -%GC_ARGS_6%
java %GC_ARGS_7% %MEM_ARGS_0% %LOGS_ARGS_7% %CP_ARGS% -%GC_ARGS_7%


