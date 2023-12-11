#!/usr/bin/env perl
use strict;

my $lines = read_lines("./input.txt"); 
my $games = [];
foreach my $line (@$lines) {
    push(@$games, parseGame($line));
}

my $maxCubes = {
    "red" => 12,
    "green" => 13,
    "blue" => 14,
};

my $sum = 0;
foreach my $game (@$games) {
    my $valid = 1; 
    foreach my $color(qw/red green blue/) {
        if(($game->{max}->{$color} // 0) > $maxCubes->{$color}) {
            $valid = 0;
            last;
        }
    }
    print $game->{gameId} ."\n";
    $sum += $game->{gameId} if $valid;
}

print $sum, "\n";

sub parseGame {
    my $line = shift;

    my @parts = split ":", $line;
    my $gameId = shift @parts;
    if($gameId =~ /(\d+)/) {
        $gameId = $1;
    }

    my $game = {
        gameId => $gameId,
        sets => [],
        max => {},
    };

    foreach my $setStr (split ";", shift @parts) {
        my $set = {};
        foreach my $cube(split ",", $setStr) {
            if($cube =~ /(\d+) ([^\s]+)/) {
                $set->{$2} = $1;
                if(!exists $game->{max}->{$2} || $game->{max}->{$2} < $1) {
                    $game->{max}->{$2} = $1;
                }
            }
        }
        push(@{$game->{sets}}, $set);
    }
    return $game;
}

sub read_lines {
    my $file = shift;
    open my $handle, '<', $file;
    chomp(my @lines = <$handle>);
    close $handle;
    return \@lines;
}