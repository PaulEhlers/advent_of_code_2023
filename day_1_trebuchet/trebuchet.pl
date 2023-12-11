#!/usr/bin/env perl

my $lines = read_lines("./input.txt");
my $sum = 0;

foreach my $line (@$lines) {
    my @digits = $line =~ /\d/g;
    $sum += $digits[0] . $digits[scalar @digits - 1];
}

print $sum, "\n";

sub read_lines {
    my $file = shift;
    open my $handle, '<', $file;
    chomp(my @lines = <$handle>);
    close $handle;
    return \@lines;
}