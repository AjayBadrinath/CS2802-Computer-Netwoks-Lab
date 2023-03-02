set ns [new Simulator]
set file_trace [open out.tr w]
$ns trace-all $file_trace

set nf [open out.nam w]
$ns namtrace-all $nf

set n1 [$ns node]
set n2 [$ns node]

set n3 [$ns node]
set mid [$ns node]

set n4 [$ns node]

proc finish {} {
	global ns nf file_trace
	$ns flush-trace
	close $nf
	close $file_trace
	exit 0
}
$ns  duplex-link $n1 $mid 10Mb 0ms DropTail
$ns  duplex-link $n2 $mid 1000Mb 0.1ms DropTail

$ns  duplex-link $n3 $mid 103Mb 10ms DropTail

$ns  duplex-link $n4 $mid 10Mb 10ms DropTail

set udp0 [new Agent/UDP]
$ns attach-agent $n1 $udp0
set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize_ 500
$cbr0 set interval_ 0.001
$cbr0 attach-agent $udp0
set null0 [new Agent/Null]
$ns attach-agent  $n4 $null0
$ns connect $udp0 $null0

set tcp0 [new Agent/TCP]
$ns attach-agent $n2 $tcp0
set cbr1 [new Application/Traffic/CBR]
$cbr1 set packetSize_ 500
$cbr1 set interval_ 0.001
$cbr1 attach-agent $tcp0
set tcpsink0 [new Agent/TCPSink]
$ns attach-agent $n3 $tcpsink0
$ns connect $tcp0 $tcpsink0

$tcp0 set fid_ 1
$udp0 set fid_ 2
 $ns color 1 Green 
 $ns color 2 Blue
 $ns at 0.1 "$cbr0 start"
 $ns at 2.5 "$cbr0 stop"
 $ns at 0.3 "$cbr1 start"
 $ns at 5.1 "$cbr1 stop"
 $ns at 5.3 "finish"
 $ns run 
 
