set ns [new Simulator]
$ns rtproto LS

set node1 [$ns node]
set node2 [$ns node]
set node3 [$ns node]
set node4 [$ns node]
set tf [open out.tr w]
$ns trace-all $tf
set nf [open out.nam w]
$ns namtrace-all $nf
proc finish {} {
 
global ns nf
$ns flush-trace
close $nf
exec nam out.nam &
exit 0
 
}
$node1 label "node1"
$node2 label "node1"
$node3 label "node1"
$ns duplex-link $node1 $node2 1.0Mb 10ms DropTail
$ns duplex-link $node2 $node3 1.0Mb 10ms DropTail
$ns duplex-link $node3 $node4 1.0Mb 10ms DropTail
$ns duplex-link $node4 $node1 1.0Mb 10ms DropTail
set tcp0 [new Agent/TCP]
$ns attach-agent $node1 $tcp0
set sink0 [new Agent/TCPSink]
$ns attach-agent $node4 $sink0
$ns connect $tcp0 $sink0

set traffic [new Application/FTP]
$traffic attach-agent $tcp0
$ns at 0.5 "$traffic start"
$ns rtmodel-at 1.0 down $node2 $node3
$ns rtmodel-at 2.0 up $node2 $node3
ns at 3.0 "$traffic start"
ns at 4.0 "$traffic stop"
ns at 5.0 "finish"
$ns run

