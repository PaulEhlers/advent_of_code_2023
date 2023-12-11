#!/usr/bin/env perl

my $lines = read_lines("./input.txt");
my $sum = 0;
my $numbers_map = {
    "one" => 1,
    "two" => 2,
    "three" => 3,
    "four" => 4,
    "five" => 5,
    "six" => 6,
    "seven" => 7,
    "eight" => 8,
    "nine" => 9,
};

foreach my $line (@$lines) {
    my ($fir, $sec) = get_digits($line);
    $sum += $fir . $sec;
}

print $sum, "\n";

sub get_digits {
    my $line = shift;

    my $first;
    FIRST:
    for(my $i = 0; $i < length($line); ++$i) {
        foreach my $number_str (keys %$numbers_map) {
            my $number = $numbers_map->{$number_str};
            for(my $j = 0; $j < length($number_str); ++$j) {
                if(charAt($line, $i+$j) =~ /\d/) {
                    $first = charAt($line, $i+$j);
                    last FIRST;
                }
                if($j == length($number_str) - 1 && charAt($line, $i+$j) eq charAt($number_str, $j)) {
                    $first = $number;
                    last FIRST;
                }
                unless(charAt($line, $i+$j) eq charAt($number_str, $j)) {
                    last;
                }
            }
        }    
    }

    
    $line = reverse($line);
    my $second;
    SECOND:
    for(my $i = 0; $i < length($line); ++$i) {
        foreach my $number_str (keys %$numbers_map) {
            my $number = $numbers_map->{$number_str};
            my $num_str_rev = reverse($number_str);
            for(my $j = 0; $j < length($num_str_rev); ++$j) {
                if(charAt($line, $i+$j) =~ /\d/) {
                    $second = charAt($line, $i+$j);
                    last SECOND;
                }
                if($j == length($num_str_rev) - 1 && charAt($line, $i+$j) eq charAt($num_str_rev, $j)) {
                    $second = $number;
                    last SECOND;
                }
                unless(charAt($line, $i+$j) eq charAt($num_str_rev, $j)) {
                    last;
                }
            }
        }    
    }

    return ($first, $second);
}

sub charAt {
    my $str = shift;
    my $i = shift;
    return substr( $str, $i , 1 );
}

sub read_lines {
    my $file = shift;
    open my $handle, '<', $file;
    chomp(my @lines = <$handle>);
    close $handle;
    return \@lines;
}