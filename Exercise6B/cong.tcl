set ns [new Simulator]
set f [open congestion.tr w]
$ns trace-all $f
set nf [open congestion.nam w]
$ns namtrace-all $nf
proc finish {} {
 exec nam congestion.nam &
exit 0
}
set n0 [$ns node]
set n1 [$ns node]

$ns duplex-link $n1 $n0 1Mb 5ms DropTail

set tcp1 [new Agent/TCP/Reno]
$ns attach-agent $n0 $tcp1
$tcp1 set fid_ 1


set sink1 [new Agent/TCPSink]
$ns attach-agent $n1 $sink1

$ns connect $tcp1 $sink1
set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1
$ftp1 set type_ FTP

set p0 [new Agent/Ping]
$ns attach-agent $n0 $p0
set p1 [new Agent/Ping]
$ns attach-agent $n1 $p1
$ns connect $p0 $p1
Agent/Ping instproc recv {from rtt} {
$self instvar node_
puts "node [$node_ id] received ping answer from \
$from with round-trip-time $rtt ms."
}
$ns at 0.5 "$p0 send"
$ns at 0.8 "$p1 send"
$ns at 1.0   "$ftp1 start"
$ns at 70.0 "$ftp1 stop"
$ns at 70.1 "$p0 send"
$ns at 70.2 "$p1 send"
$ns at 80.0 "finish" 


$ns run 
